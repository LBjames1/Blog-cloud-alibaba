package com.lauz.blogcloud.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleVO {

    private Integer id;

    @ApiModelProperty(value = "作者")
    private Integer userId;

    @ApiModelProperty(value = "文章分类")
    private Integer categoryId;

    @ApiModelProperty(value = "文章缩略图")
    private String articleCover;

    @ApiModelProperty(value = "标题")
    private String articleTitle;

    @ApiModelProperty(value = "发表时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否置顶")
    private Integer isTop;

    @ApiModelProperty(value = "是否为草稿")
    private Integer isDraft;

    @ApiModelProperty(value = "内容")
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
     * 文章标签
     */
    private List<TagVO> tagVOList;
}
