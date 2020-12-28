package com.lauz.blogcloud.service;

import com.lauz.blogcloud.common.domain.UserDto;
import com.lauz.blogcloud.model.BlogUser;

public interface UserService {

    BlogUser getUserByUserName(String username);

    /**
     * 根据用户名获取后台用户信息
     * @param username
     * @return
     */
    UserDto loadUserByUsername(String username);

    BlogUser getCurrentuser();
}
