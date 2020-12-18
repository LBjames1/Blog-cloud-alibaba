package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.dto.MessageDTO;
import com.lauz.blogcloud.service.ForeMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ Description   :  前台留言信息接口
 * @ Author        :  lauz
 * @ CreateDate    :  2020年12月18日09:30
 */
@Api(tags = "ForeMessageController",description = "留言信息接口")
@RestController
@RequestMapping("/message")
public class ForeMessageController {

    @Autowired
    private ForeMessageService messageService;

    @ApiOperation("留言展示")
    @GetMapping("/list")
    public CommonResult get(){
        return CommonResult.success(messageService.getMessages());
    }

    @ApiOperation("新增留言")
    @PostMapping("/save")
    public CommonResult save(@RequestBody MessageDTO message){
        return CommonResult.success(messageService.saveMessage(message));
    }
}
