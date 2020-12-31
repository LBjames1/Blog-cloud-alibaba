package com.lauz.blogcloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.lauz.blogcloud.mapper.BlogMessageMapper;
import com.lauz.blogcloud.model.BlogMessage;
import com.lauz.blogcloud.model.BlogMessageExample;
import com.lauz.blogcloud.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private BlogMessageMapper messageMapper;

    @Override
    public int delete(Integer id) {
        return messageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<BlogMessage> getMessageList(BlogMessage message, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        BlogMessageExample example = new BlogMessageExample();
        example.setOrderByClause(message.getCreateTime().toString());
        return messageMapper.selectByExample(example);
    }
}
