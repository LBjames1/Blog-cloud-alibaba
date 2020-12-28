package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.domain.UserDto;
import com.lauz.blogcloud.model.BlogUser;
import com.lauz.blogcloud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "UserController", description = "后台用户管理")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("根据用户名获取通用用户信息")
    @GetMapping(value = "/loadByUsername")
    public UserDto loadUserByUsername(@RequestParam String username) {
        UserDto userDTO = userService.loadUserByUsername(username);
        return userDTO;
    }

    @ApiOperation("获取博主信息")
    @GetMapping(value = "/getMySelfInfo")
    public BlogUser getMySelfInfo() {
        String username = "admin";
        BlogUser user = userService.getUserByUserName(username);
        return user;
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping(value = "/getCurrentUser")
    public BlogUser getCurrentUser() {
        BlogUser user = userService.getCurrentuser();
        return user;
    }

}
