package com.lauz.blogcloud.service;

import com.lauz.blogcloud.dto.ArticleSearchDTO;

import java.util.List;

public interface EsArticleSearchService {

    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();


    /**
     * 根据关键字搜索（包含文章，标题）
     */
    List<ArticleSearchDTO> search(String keyword);
}
