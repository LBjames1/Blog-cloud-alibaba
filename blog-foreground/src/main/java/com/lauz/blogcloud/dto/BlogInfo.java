package com.lauz.blogcloud.dto;

import com.lauz.blogcloud.model.BlogUser;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @ Description   :  前台首页相关博客信息
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/17 11:18
 */

@Data
@Setter
@Getter
@NoArgsConstructor
public class BlogInfo {

    /**
     * 博主信息（显示:昵称,头像，简介）
     */
    private BlogUser user;
    /**
     * 文章数量
     */
    private Long articleCount;

    /**
     * 分类数量
     */
    private Long categoryCount;

    /**
     * 标签数量
     */
    private Long tagCount;

    /**
     * 公告
     */
    private String notice;

    /**
     * 访问量
     */
    private String viewsCount;
}
