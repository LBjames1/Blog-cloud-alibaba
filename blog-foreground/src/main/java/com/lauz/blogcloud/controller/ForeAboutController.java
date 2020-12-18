package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.service.ForeAboutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Description   :  前台关于我接口
 * @ Author        :  lauz
 * @ CreateDate    :  2020年12月18日
 */
@Api(tags = "ForeAboutController",description = "关于我信息接口")
@RestController
@RequestMapping("/about")
public class ForeAboutController {

    @Autowired
    private ForeAboutService aboutService;

    @ApiOperation("关于我信息展示")
    @GetMapping("/get")
    public CommonResult get(){
        return CommonResult.success(aboutService.getAboutInfo());
    }
}
