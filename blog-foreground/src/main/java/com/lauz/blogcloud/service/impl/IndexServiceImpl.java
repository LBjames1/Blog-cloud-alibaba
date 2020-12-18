package com.lauz.blogcloud.service.impl;

import com.lauz.blogcloud.common.service.RedisService;
import com.lauz.blogcloud.dto.BlogInfo;
import com.lauz.blogcloud.feign.ForeUserService;
import com.lauz.blogcloud.mapper.BlogArticleMapper;
import com.lauz.blogcloud.mapper.BlogCategoryMapper;
import com.lauz.blogcloud.mapper.BlogTagMapper;
import com.lauz.blogcloud.model.BlogUser;
import com.lauz.blogcloud.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private ForeUserService userService;
    @Autowired
    private BlogArticleMapper articleMapper;
    @Autowired
    private BlogCategoryMapper categoryMapper;
    @Autowired
    private BlogTagMapper tagMapper;
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.notice}")
    private String notice_key;
    @Value("${redis.key.viewCount}")
    private String viewCount_key;
    @Value("${blog.default.notice}")
    private String default_notice;

    @Override
    public BlogInfo getBlogInfo() {
        BlogInfo blogInfo = new BlogInfo();
        //博主信息
        BlogUser user = userService.getMySelfInfo();
        //查询文章数量
        Long articleCount = articleMapper.countByExample(null);
        //查询分类数量
        Long categoryCount = categoryMapper.countByExample(null);
        //查询标签数量
        Long tagCount = tagMapper.countByExample(null);
        //查询公告
        String notice = redisService.get(notice_key).toString();
        notice = notice != null ? notice : default_notice;
        //查询访问量
        String viewsCount = redisService.get(viewCount_key).toString();
        blogInfo.setUser(user);
        blogInfo.setArticleCount(articleCount);
        blogInfo.setCategoryCount(categoryCount);
        blogInfo.setTagCount(tagCount);
        blogInfo.setNotice(notice);
        blogInfo.setViewsCount(viewsCount);
        return blogInfo;
    }
}
