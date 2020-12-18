package com.lauz.blogcloud.dao;

import com.lauz.blogcloud.dto.ArticleDetailDTO;
import com.lauz.blogcloud.dto.ForeArticleDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForeArticleDao {

    /**
     * 分页查询文章数据
     * @return
     */
    List<ForeArticleDTO> getArticleList(@Param("current") Long current);

    /**
     *获取文章详情
     */
    ArticleDetailDTO getArticle(@Param("id") Integer id);
}
