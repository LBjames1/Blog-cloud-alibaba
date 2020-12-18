package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.service.ForeTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Description   :  前台标签信息接口
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/16 17:44
 */
@Api(tags = "ForeArticleTagCortroller",description = "标签信息接口")
@RestController
@RequestMapping("/tag")
public class ForeArticleTagCortroller {

    @Autowired
    private ForeTagService tagService;

    @ApiOperation("标签展示")
    @GetMapping("/list")
    public CommonResult get(){
        return CommonResult.success(tagService.getTagDtos());
    }

    @ApiOperation("标签数量")
    @GetMapping("/count")
    public CommonResult count(){
        return CommonResult.success(tagService.tagCount());
    }
}
