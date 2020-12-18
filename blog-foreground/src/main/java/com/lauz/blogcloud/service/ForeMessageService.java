package com.lauz.blogcloud.service;

import com.lauz.blogcloud.dto.MessageDTO;

import java.util.List;

/**
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/18 9:22
 */

public interface ForeMessageService {

    /**
     * 查看留言弹幕
     *
     * @return 留言列表
     */
    List<MessageDTO> getMessages();

    Integer saveMessage(MessageDTO message);
}
