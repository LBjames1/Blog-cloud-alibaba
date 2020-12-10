package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.domain.UserDto;
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
}
