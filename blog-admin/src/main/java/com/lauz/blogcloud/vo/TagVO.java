package com.lauz.blogcloud.vo;

import lombok.Data;

@Data
public class TagVO {

    private Integer articleTagId;
    /**
     * 标签id
     */
    private Integer id;

    /**
     * 标签名
     */
    private String tagName;
}
