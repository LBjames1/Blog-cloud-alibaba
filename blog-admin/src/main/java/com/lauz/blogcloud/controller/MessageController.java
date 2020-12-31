package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonPage;
import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.model.BlogCategory;
import com.lauz.blogcloud.model.BlogMessage;
import com.lauz.blogcloud.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "留言api")
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/page")
    @ApiOperation("留言分页查询")
    public CommonResult<CommonPage<BlogMessage>> pageQuery(BlogMessage message,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<BlogMessage> messages = messageService.getMessageList(message,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(messages));
    }


    @DeleteMapping("/del/{id}")
    @ApiOperation("删除留言")
    public CommonResult del(@PathVariable("id") Integer id){
        int result = messageService.delete(id);
        if(result>0){
            return CommonResult.success("删除成功","success");
        }else{
            return CommonResult.failed();
        }
    }
}
