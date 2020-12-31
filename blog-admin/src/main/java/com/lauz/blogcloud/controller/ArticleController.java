package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonPage;
import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.service.ArticleService;
import com.lauz.blogcloud.vo.ArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "文章管理api")
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    @ApiOperation("添加文章")
    public CommonResult add(@RequestBody ArticleVO articleVO){
        int result = articleService.save(articleVO);
        if(result>0){
            return CommonResult.success("添加成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update")
    @ApiOperation("更新文章")
    public CommonResult update(@RequestBody ArticleVO articleVO){
        int result = articleService.save(articleVO);
        if(result>0){
            return CommonResult.success("更新成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("删除文章")
    public CommonResult del(@PathVariable("id") Integer id){
        int result = articleService.delete(id);
        if(result>0){
            return CommonResult.success("删除成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/get/{id}")
    @ApiOperation("根据id获取文章")
    public CommonResult get(@PathVariable("id") Integer id){
        return CommonResult.success(articleService.getArticle(id));
    }

    @GetMapping("/page")
    @ApiOperation("后台文章分页查询")
    public CommonResult<CommonPage<ArticleVO>> pageQuery(ArticleVO articleVO,
               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<ArticleVO> articleVOS = articleService.getArticleList(articleVO,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(articleVOS));

    }
}
