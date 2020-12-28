package com.lauz.blogcloud.feign;

import com.lauz.blogcloud.model.BlogUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient("blog-oam")
public interface ForeUserService {

    @GetMapping("/user/getMySelfInfo")
    BlogUser getMySelfInfo();

    @GetMapping("/user/getCurrentUser")
    BlogUser getCurrentUser();
}
