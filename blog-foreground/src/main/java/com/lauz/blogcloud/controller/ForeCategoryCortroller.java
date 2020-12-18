package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.service.ForeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Description   :  前台类别接口
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/16 17:44
 */
@Api(tags = "ForeCategoryCortroller",description = "分类信息接口")
@RestController
@RequestMapping("/category")
public class ForeCategoryCortroller {

    @Autowired
    private ForeCategoryService categoryService;

    @ApiOperation("分类展示")
    @GetMapping("/list")
    public CommonResult get(){
        return CommonResult.success(categoryService.getCateroryDtos());
    }

    @ApiOperation("分类数量")
    @GetMapping("/count")
    public CommonResult count(){
        return CommonResult.success(categoryService.categoryCount());
    }
}
