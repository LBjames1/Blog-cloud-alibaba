package com.lauz.blogcloud.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lauz.blogcloud.common.service.RedisService;
import com.lauz.blogcloud.dao.ForeCateroryDao;
import com.lauz.blogcloud.dto.CategoryDTO;
import com.lauz.blogcloud.mapper.BlogCategoryMapper;
import com.lauz.blogcloud.service.ForeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForeCategoryServiceImpl implements ForeCategoryService {

    @Autowired
    private ForeCateroryDao cateroryDao;
    @Autowired
    private BlogCategoryMapper categoryMapper;
    @Autowired
    private RedisService redisService;
    @Value("redis.key.categoryCount")
    private String categoryCount;
    @Value("redis.expire.common")
    private String expireTime;


    @Override
    public List<CategoryDTO> getCateroryDtos() {
        return cateroryDao.getCateroryDtos();
    }

    @Override
    public Long categoryCount() {
        if(ObjectUtil.isNotNull(redisService.get(categoryCount))){
            return (Long)redisService.get(categoryCount);
        }else{
            Long count = categoryMapper.countByExample(null);
            redisService.set(categoryCount,count,Long.parseLong(expireTime));
            return count;
        }
    }
}
