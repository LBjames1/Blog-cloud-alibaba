package com.lauz.blogcloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.lauz.blogcloud.mapper.BlogArticleMapper;
import com.lauz.blogcloud.mapper.BlogArticleTagMapper;
import com.lauz.blogcloud.model.BlogArticle;
import com.lauz.blogcloud.model.BlogArticleExample;
import com.lauz.blogcloud.service.ArticleService;
import com.lauz.blogcloud.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private BlogArticleMapper articleMapper;

    @Override
    public int save(ArticleVO articleVO) {
        BlogArticle article = new BlogArticle();
        BeanUtil.copyProperties(articleVO,article);
        article.setCreateTime(DateUtil.date());
        article.setUpdateTime(DateUtil.date());
        return articleMapper.insert(article);
    }

    @Override
    public int update(ArticleVO articleVO) {
        BlogArticle article = new BlogArticle();
        BeanUtil.copyProperties(articleVO,article);
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public int delete(Integer id) {
        BlogArticle article = new BlogArticle();
        article.setId(id);
        article.setIsDelete(1);
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public List<ArticleVO> getArticleList(ArticleVO articleVO,Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        BlogArticleExample example = new BlogArticleExample();
        BlogArticleExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        if(StrUtil.isNotBlank(articleVO.getArticleTitle())){
            criteria.andArticleTitleLike(articleVO.getArticleTitle());
        }
        if(articleVO.getCategoryId()!=null){
            criteria.andCategoryIdEqualTo(articleVO.getCategoryId());
        }
        List<BlogArticle> articles = articleMapper.selectByExample(example);
        List<ArticleVO> articleVOS = new ArrayList<>();
        BeanUtil.copyProperties(articles,articleVOS);
        return articleVOS;
    }

    @Override
    public ArticleVO getArticle(Integer id) {
        BlogArticle article = articleMapper.selectByPrimaryKey(id);
        ArticleVO vo = new ArticleVO();
        BeanUtil.copyProperties(article,vo);
        return vo;
    }
}
