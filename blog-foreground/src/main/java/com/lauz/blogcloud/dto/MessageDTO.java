package com.lauz.blogcloud.dto;

import lombok.Data;

/**
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/18
 */

@Data
public class MessageDTO {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 留言内容
     */
    private String messageContent;

    /**
     * 弹幕速度
     */
    private Integer time;

}
