package com.lauz.blogcloud.dto;

import com.lauz.blogcloud.model.BlogTag;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @ Description   :  前台首页文章信息
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/17 11:18
 */
@Data
@Setter
@Getter
@NoArgsConstructor
public class ForeArticleDTO {

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
     * 发表时间
     */
    private Date createTime;

    /**
     * 是否置顶
     */
    private Boolean isTop;

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
