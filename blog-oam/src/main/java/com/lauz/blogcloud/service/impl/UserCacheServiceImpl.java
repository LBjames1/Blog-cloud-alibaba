package com.lauz.blogcloud.service.impl;

import com.lauz.blogcloud.common.service.RedisService;
import com.lauz.blogcloud.model.BlogUser;
import com.lauz.blogcloud.service.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserCacheServiceImpl implements UserCacheService {

    @Autowired
    private RedisService redisService;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.loginUser}")
    private String REDIS_KEY_USER;


    @Override
    public void delUserCache(Integer userId) {
        String key = REDIS_KEY_USER + ":" + userId;
        redisService.del(key);
    }

    @Override
    public BlogUser getUserCache(Integer userId) {
        String key = REDIS_KEY_USER + ":" + userId;
        return (BlogUser) redisService.get(key);
    }

    @Override
    public void setUserCache(BlogUser user) {
        String key = REDIS_KEY_USER + ":" + user.getId();
        redisService.set(key,user,REDIS_EXPIRE);
    }
}
