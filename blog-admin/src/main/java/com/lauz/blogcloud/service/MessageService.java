package com.lauz.blogcloud.service;

import com.lauz.blogcloud.model.BlogMessage;

import java.util.List;

public interface MessageService {

    int delete(Integer id);

    List<BlogMessage> getMessageList(BlogMessage message, Integer pageSize, Integer pageNum);
}
