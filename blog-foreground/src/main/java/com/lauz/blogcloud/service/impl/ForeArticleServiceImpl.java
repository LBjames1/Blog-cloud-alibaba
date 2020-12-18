package com.lauz.blogcloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.lauz.blogcloud.common.service.RedisService;
import com.lauz.blogcloud.common.util.HTMLUtil;
import com.lauz.blogcloud.dao.ForeArticleDao;
import com.lauz.blogcloud.dto.ArticleDetailDTO;
import com.lauz.blogcloud.dto.ForeArticleDTO;
import com.lauz.blogcloud.mapper.BlogArticleMapper;
import com.lauz.blogcloud.mapper.BlogArticleTagMapper;
import com.lauz.blogcloud.mapper.BlogTagMapper;
import com.lauz.blogcloud.model.*;
import com.lauz.blogcloud.service.ForeArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForeArticleServiceImpl implements ForeArticleService {

    @Autowired
    private ForeArticleDao articleDao;
    @Autowired
    private BlogTagMapper tagMapper;
    @Autowired
    private BlogArticleTagMapper articleTagMapper;
    @Value("${redis.key.articleViewCount}")
    private String viewCount_key;
    @Value("${redis.key.articleLikeCount}")
    private String likeCount_key;
    @Autowired
    private RedisService redisService;
    @Autowired
    private BlogArticleMapper articleMapper;


    @Override
    public List<ForeArticleDTO> getArticleList(Long current) {
        List<ForeArticleDTO> articleDTOS = articleDao.getArticleList(current-1);
        articleDTOS.stream().forEach(foreArticleDTO -> {
            BlogArticleTagExample example = new BlogArticleTagExample();
            example.createCriteria().andArticleIdEqualTo(foreArticleDTO.getId());
            List<BlogArticleTag> articleTags = articleTagMapper.selectByExample(example);
            BlogTagExample tagExample = new BlogTagExample();
            List<Integer> tagIds = articleTags.stream().map(tag -> tag.getTagId()).collect(Collectors.toList());
            tagExample.createCriteria().andIdIn(tagIds);
            List<BlogTag> tags = tagMapper.selectByExample(tagExample);
            foreArticleDTO.setTagList(tags);
            foreArticleDTO.setArticleContent(HTMLUtil.deleteArticleTag(foreArticleDTO.getArticleContent()));
        });
        return articleDTOS;
    }

    @Override
    public ArticleDetailDTO getArticle(Integer id) {
        if(!redisService.hHasKey(viewCount_key,id.toString())){
            redisService.hSet(viewCount_key,id.toString(),0);
        }
        redisService.hIncr(viewCount_key,id.toString(),1L);
        ArticleDetailDTO detailDTO = articleDao.getArticle(id);
        BlogArticleTagExample example = new BlogArticleTagExample();
        example.createCriteria().andArticleIdEqualTo(id);
        List<BlogArticleTag> articleTags = articleTagMapper.selectByExample(example);
        BlogTagExample tagExample = new BlogTagExample();
        List<Integer> tagIds = articleTags.stream().map(tag -> tag.getTagId()).collect(Collectors.toList());
        tagExample.createCriteria().andIdIn(tagIds);
        List<BlogTag> tags = tagMapper.selectByExample(tagExample);
        detailDTO.setTagList(tags);
        detailDTO.setViewsCount((Integer) redisService.hGet(viewCount_key,id.toString()));
        detailDTO.setLikeCount((Integer) redisService.hGet(likeCount_key,id.toString()));
        return detailDTO;
    }

    @Override
    public List<ForeArticleDTO> getArchiveArticleList(Integer current) {
        PageHelper.startPage(current, 10);
        BlogArticleExample example = new BlogArticleExample();
        example.createCriteria().andIsDeleteEqualTo(0).andIsDraftEqualTo(0);
        example.setOrderByClause("create_time desc");
        List<BlogArticle> articles = articleMapper.selectByExample(example);
        List<ForeArticleDTO> articleDTOS = new ArrayList<>();
        articles.stream().forEach(article ->{
            ForeArticleDTO articleDTO = new ForeArticleDTO();
            BeanUtil.copyProperties(article,articleDTO);
            articleDTOS.add(articleDTO);
        });
        return articleDTOS;
    }
}
