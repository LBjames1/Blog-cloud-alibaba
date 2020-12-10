package com.lauz.blogcloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.lauz.blogcloud.common.domain.UserDto;
import com.lauz.blogcloud.mapper.BlogUserMapper;
import com.lauz.blogcloud.model.BlogUser;
import com.lauz.blogcloud.model.BlogUserExample;
import com.lauz.blogcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BlogUserMapper userMapper;

    @Override
    public UserDto loadUserByUsername(String username) {
        BlogUserExample example = new BlogUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<BlogUser> adminList = userMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            UserDto dto = new UserDto();
            BeanUtil.copyProperties(adminList.get(0),dto);
            return dto;
        }
        return null;
    }
}
