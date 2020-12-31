package com.lauz.blogcloud.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lauz.blogcloud.common.service.RedisService;
import com.lauz.blogcloud.mapper.BlogAboutInfoMapper;
import com.lauz.blogcloud.model.BlogAboutInfo;
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
    @Value("${blog.default.notice}")
    private String default_notice;

    @Override
    public String getAboutInfo() {
        if(ObjectUtil.isNotNull(redisService.get(aboutMe))){
            return redisService.get(aboutMe).toString();
        }else{
            String about = default_notice;
            BlogAboutInfo aboutInfo = aboutInfoMapper.selectByExample(null).get(0);
            if(aboutInfo!=null){
                about = aboutInfo.getAboutContent();
            }
            redisService.set(aboutMe,about,Long.parseLong(expireTime));
            return about;
        }
    }
}
