package com.lauz.blogcloud.service;


import com.lauz.blogcloud.vo.ArticleVO;

import java.util.List;

public interface ArticleService {

    int save(ArticleVO articleVO);

    int update(ArticleVO articleVO);

    int delete(Integer id);

    List<ArticleVO> getArticleList(ArticleVO articleVO,Integer pageSize, Integer pageNum);

    ArticleVO getArticle(Integer id);
}
