package com.lauz.blogcloud.dao;

import com.lauz.blogcloud.dto.ArticleSearchDTO;

import java.util.List;

public interface EsArticleDao {

    List<ArticleSearchDTO> getEsArticles();
}
