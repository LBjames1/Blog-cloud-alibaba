package com.lauz.blogcloud.dto;

import com.lauz.blogcloud.model.BlogTag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ Description   :  前台文章详情
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/17 15:30
 */

@Data
public class ArticleDetailDTO {

    /**
     * id
     */
    private Integer id;

    /**
     * 文章缩略图
     */
    private String articleCover;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 内容
     */
    private String articleContent;

    /**
     * 点赞量
     */
    private Integer likeCount;

    /**
     * 浏览量
     */
    private Integer viewsCount;

    /**
     * 发表时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 文章分类id
     */
    private Integer categoryId;

    /**
     * 文章分类名
     */
    private String categoryName;

    /**
     * 文章标签
     */
    private List<BlogTag> tagList;

}
