package com.lauz.blogcloud.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lauz.blogcloud.common.service.RedisService;
import com.lauz.blogcloud.mapper.BlogAboutInfoMapper;
import com.lauz.blogcloud.service.ForeAboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ForeAboutServiceImpl implements ForeAboutService {

    @Autowired
    private BlogAboutInfoMapper aboutInfoMapper;
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.aboutMe}")
    private String aboutMe;
    @Value("${redis.expire.common}")
    private String expireTime;

    @Override
    public String getAboutInfo() {
        if(ObjectUtil.isNotNull(redisService.get(aboutMe))){
            return redisService.get(aboutMe).toString();
        }else{
            String about = aboutInfoMapper.selectByExample(null).get(0).getAboutContent();
            redisService.set(aboutMe,about,Long.parseLong(expireTime)*7);
            return about;
        }
    }
}
