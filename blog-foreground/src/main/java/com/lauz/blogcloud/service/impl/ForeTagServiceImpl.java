package com.lauz.blogcloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.lauz.blogcloud.common.service.RedisService;
import com.lauz.blogcloud.dto.TagDTO;
import com.lauz.blogcloud.mapper.BlogTagMapper;
import com.lauz.blogcloud.model.BlogTag;
import com.lauz.blogcloud.service.ForeTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForeTagServiceImpl implements ForeTagService {

    @Autowired
    private BlogTagMapper tagMapper;
    @Autowired
    private RedisService redisService;
    @Value("redis.key.tagCount")
    private String tagCount;
    @Value("redis.expire.common")
    private String expireTime;

    @Override
    public List<TagDTO> getTagDtos() {
        List<BlogTag> tags = tagMapper.selectByExample(null);
        List<TagDTO> tagDTOS = new ArrayList<>();
        tags.stream().forEach(blogTag -> {
            TagDTO dto = new TagDTO();
            BeanUtil.copyProperties(blogTag,dto);
            tagDTOS.add(dto);
        });
        return tagDTOS;
    }

    @Override
    public Long tagCount() {
        if(ObjectUtil.isNotNull(redisService.get(tagCount))){
            return (Long)redisService.get(tagCount);
        }else{
            Long count = tagMapper.countByExample(null);
            redisService.set(tagCount,count,Long.parseLong(expireTime));
            return count;
        }
    }
}
