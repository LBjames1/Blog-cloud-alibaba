package com.lauz.blogcloud.service;

import com.lauz.blogcloud.model.BlogArticleTag;
import com.lauz.blogcloud.vo.TagVO;

import java.util.List;

public interface ArticleTagService {

    int save(BlogArticleTag articleTag);

    int save(Integer articleId, List<TagVO> tags);

    int delete(Integer id);

    List<TagVO> get(Integer articleId);

}
