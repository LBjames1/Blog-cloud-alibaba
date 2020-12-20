package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonPage;
import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.service.ForeArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ Description   :  前台文章信息接口
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/16 17:44
 */
@Api(tags = "前台文章信息接口")
@RestController
@RequestMapping("/article")
public class ForeArticleCortroller {

    @Autowired
    private ForeArticleService articleService;

    @ApiOperation("首页文章展示")
    @GetMapping("/list/{current}")
    public CommonResult get(@PathVariable("current") Long current){
        return CommonResult.success(articleService.getArticleList(current));
    }

    @ApiOperation("首页文章详情")
    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") Integer id){
        return CommonResult.success(articleService.getArticle(id));
    }

    @ApiOperation("归档文章")
    @GetMapping("/archive/list/{current}")
    public CommonResult archiveList(@PathVariable("current") Integer current){
        return CommonResult.success(CommonPage.restPage(articleService.getArchiveArticleList(current)));
    }

    @ApiOperation("文章点赞")
    @PostMapping("/like/{id}")
    public CommonResult like(@PathVariable("id") Integer id){
        articleService.saveLike(id);
        return CommonResult.success("点赞成功");
    }
}
