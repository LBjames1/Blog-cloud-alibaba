package com.lauz.blogcloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.lauz.blogcloud.common.service.RedisService;
import com.lauz.blogcloud.common.util.HTMLUtil;
import com.lauz.blogcloud.dao.ForeArticleDao;
import com.lauz.blogcloud.dto.ArticleDetailDTO;
import com.lauz.blogcloud.dto.ForeArticleDTO;
import com.lauz.blogcloud.feign.ForeUserService;
import com.lauz.blogcloud.mapper.BlogArticleMapper;
import com.lauz.blogcloud.mapper.BlogArticleTagMapper;
import com.lauz.blogcloud.mapper.BlogTagMapper;
import com.lauz.blogcloud.model.*;
import com.lauz.blogcloud.service.ForeArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    @Value("${redis.key.userLikeSet}")
    private String userLikeSet;
    @Autowired
    private RedisService redisService;
    @Autowired
    private BlogArticleMapper articleMapper;
    @Autowired
    private ForeUserService userService;


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

    @Override
    public void saveLike(Integer id) {
        BlogUser user = userService.getCurrentUser();
        //查询当前用户点赞过的文章id集合
        Set<Integer> articleLikeSet = (Set<Integer>) redisService.hGet(userLikeSet,user.getId().toString());
        //第一次点赞则创建
        if (articleLikeSet == null) {
            articleLikeSet = new HashSet<Integer>();
        }
        //判断是否点赞
        if (articleLikeSet.contains(id)) {
            //点过赞则删除文章id
            articleLikeSet.remove(id);
            //文章点赞量-1
            redisService.hDecr(likeCount_key,id.toString(),1L);
        } else {
            //未点赞则增加文章id
            articleLikeSet.add(id);
            //文章点赞量+1
            redisService.hIncr(likeCount_key,id.toString(),1L);
        }
        //保存点赞记录
        redisService.hSet(userLikeSet,user.getId().toString(),articleLikeSet);
    }
}
