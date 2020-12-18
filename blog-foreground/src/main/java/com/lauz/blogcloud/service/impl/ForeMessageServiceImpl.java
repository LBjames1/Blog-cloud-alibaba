package com.lauz.blogcloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.lauz.blogcloud.common.util.IpUtil;
import com.lauz.blogcloud.dto.MessageDTO;
import com.lauz.blogcloud.mapper.BlogMessageMapper;
import com.lauz.blogcloud.model.BlogMessage;
import com.lauz.blogcloud.service.ForeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForeMessageServiceImpl implements ForeMessageService {

    @Autowired
    private BlogMessageMapper messageMapper;
    @Autowired
    private HttpServletRequest request;

    @Override
    public List<MessageDTO> getMessages() {
        List<BlogMessage> messages = messageMapper.selectByExample(null);
        List<MessageDTO> dtos = new ArrayList<>();
        messages.stream().forEach(mes ->{
            MessageDTO messageDTO = new MessageDTO();
            BeanUtil.copyProperties(mes,messageDTO);
            dtos.add(messageDTO);
        });
        return dtos;
    }

    @Override
    @Transactional
    public Integer saveMessage(MessageDTO message) {
        String ipAddr = IpUtil.getIpAddr(request);
        String ipSource = IpUtil.getIpSource(ipAddr);
        BlogMessage mes = new BlogMessage();
        BeanUtil.copyProperties(message,mes);
        mes.setIpAddress(ipAddr);
        mes.setIpSource(ipSource);
        mes.setCreateTime(DateUtil.date());
        return messageMapper.insert(mes);
    }
}
