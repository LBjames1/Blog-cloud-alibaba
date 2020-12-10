package com.lauz.blogcloud.service;

import com.lauz.blogcloud.common.domain.UserDto;

public interface UserService {

    /**
     * 根据用户名获取后台用户信息
     * @param username
     * @return
     */
    UserDto loadUserByUsername(String username);
}
