package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.service.EsArticleSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ Description   :  搜索集成es
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/18 15:26
 */
@Api(tags = "EsArticleSearchController",description = "es搜索文章")
@RestController
@RequestMapping("/es")
public class EsArticleSearchController {

    @Autowired
    private EsArticleSearchService searchService;

    @ApiOperation("根据关键词搜索")
    @GetMapping("/search/{keyword}")
    public CommonResult search(@PathVariable("keyword") String keyword){
        return CommonResult.success(searchService.search(keyword));
    }

    @ApiOperation("导入mysql中全部文章数据")
    @PostMapping("/import")
    public CommonResult importAll(){
        return CommonResult.success(searchService.importAll());
    }
}
