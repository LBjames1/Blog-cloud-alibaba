package com.lauz.blogcloud.auth.service;

import com.lauz.blogcloud.common.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 */
@FeignClient("blog-oam")
@Component
public interface UserAdminService {

    @GetMapping("/user/loadByUsername")
    UserDto loadUserByUsername(@RequestParam("username") String username);
}
