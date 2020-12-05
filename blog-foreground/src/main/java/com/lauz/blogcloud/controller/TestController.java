package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Blog-cloud-alibaba
 * @description: test
 * @author: lauz
 * @create: 2020-12-05 22:01
 **/
@Api(tags = "测试")
@RestController
public class TestController {

    @GetMapping("/test")
    @ApiOperation("测试方法")
    public CommonResult test(){
        return CommonResult.success("success");
    }
}
