package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonPage;
import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.model.BlogComment;
import com.lauz.blogcloud.model.BlogMessage;
import com.lauz.blogcloud.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "评论api")
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/page")
    @ApiOperation("评论分页查询")
    public CommonResult<CommonPage<BlogComment>> pageQuery(BlogComment comment,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<BlogComment> comments = commentService.getCommentList(comment,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(comments));
    }


    @DeleteMapping("/del/{id}")
    @ApiOperation("删除评论")
    public CommonResult del(@PathVariable("id") Integer id){
        int result = commentService.delete(id);
        if(result>0){
            return CommonResult.success("删除成功","success");
        }else{
            return CommonResult.failed();
        }
    }
}
