package com.lauz.blogcloud.service;

import com.lauz.blogcloud.model.BlogUser;

/**
 * 用户缓存操作
 */
public interface UserCacheService {

    /**
     * 删除用户缓存
     */
    void delUserCache(Integer userId);

    /**
     * 获取缓存用户信息
     */
    BlogUser getUserCache(Integer userId);

    /**
     * 设置缓存用户信息
     */
    void setUserCache(BlogUser user);
}
