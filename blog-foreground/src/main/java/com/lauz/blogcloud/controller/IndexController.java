package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Description   :  首页部分信息获取
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/16 17:33
 */
@RestController
@RequestMapping("/index")
@Api(tags = "IndexController", description = "前台首页部分信息接口")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @ApiOperation("博客信息")
    @GetMapping("/getBlogInfo")
    public CommonResult getMySelfInfo(){
        return CommonResult.success(indexService.getBlogInfo());
    }
}
