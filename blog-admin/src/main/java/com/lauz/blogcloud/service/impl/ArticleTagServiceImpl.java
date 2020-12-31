package com.lauz.blogcloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.lauz.blogcloud.mapper.BlogArticleTagMapper;
import com.lauz.blogcloud.mapper.BlogTagMapper;
import com.lauz.blogcloud.model.BlogArticleTag;
import com.lauz.blogcloud.model.BlogArticleTagExample;
import com.lauz.blogcloud.model.BlogTag;
import com.lauz.blogcloud.service.ArticleTagService;
import com.lauz.blogcloud.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Autowired
    private BlogArticleTagMapper articleTagMapper;
    @Autowired
    private BlogTagMapper tagMapper;

    @Override
    public int save(BlogArticleTag articleTag) {
        return articleTagMapper.insert(articleTag);
    }

    @Override
    public int save(Integer articleId, List<TagVO> tags) {
        tags.stream().forEach(tagVO -> {
            BlogArticleTag articleTag = new BlogArticleTag();
            articleTag.setArticleId(articleId);
            articleTag.setTagId(tagVO.getId());
            save(articleTag);
        });
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return articleTagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TagVO> get(Integer articleId) {
        BlogArticleTagExample example = new BlogArticleTagExample();
        BlogArticleTagExample.Criteria criteria = example.createCriteria();
        criteria.andArticleIdEqualTo(articleId);
        List<BlogArticleTag> articleTags = articleTagMapper.selectByExample(example);
        List<TagVO> tagVOS = new ArrayList<>();
        articleTags.stream().forEach(articleTag -> {
            TagVO vo = new TagVO();
            BlogTag tag = tagMapper.selectByPrimaryKey(articleTag.getTagId());
            BeanUtil.copyProperties(tag,vo);
            vo.setArticleTagId(articleTag.getId());
            tagVOS.add(vo);
        });
        return tagVOS;
    }
}
