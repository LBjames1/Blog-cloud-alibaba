package com.lauz.blogcloud.service;

import com.lauz.blogcloud.dto.ArticleDetailDTO;
import com.lauz.blogcloud.dto.ForeArticleDTO;

import java.util.List;

public interface ForeArticleService {

    List<ForeArticleDTO> getArticleList(Long current);

    ArticleDetailDTO getArticle(Integer id);

    List<ForeArticleDTO> getArchiveArticleList(Integer current);

    void saveLike(Integer id);
}
