package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.service.EsArticleSearchService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Description   :  搜索集成es
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/18 15:26
 */
@Api(tags = "EsArticleSearchController",description = "es搜索文章")
@RestController
@RequestMapping("/search")
public class EsArticleSearchController {

    @Autowired
    private EsArticleSearchService searchService;
}
