package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonPage;
import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.model.BlogCategory;
import com.lauz.blogcloud.service.CategoryService;
import com.lauz.blogcloud.vo.ArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "文章分类api")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    @ApiOperation("添加分类")
    public CommonResult add(@RequestBody BlogCategory category){
        int result = categoryService.save(category);
        if(result>0){
            return CommonResult.success("添加成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update")
    @ApiOperation("更新分类")
    public CommonResult update(@RequestBody BlogCategory category){
        int result = categoryService.update(category);
        if(result>0){
            return CommonResult.success("更新成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("删除分类")
    public CommonResult del(@PathVariable("id") Integer id){
        int result = categoryService.delete(id);
        if(result>0){
            return CommonResult.success("删除成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/get/{id}")
    @ApiOperation("根据id获取分类")
    public CommonResult get(@PathVariable("id") Integer id){
        return CommonResult.success(categoryService.getCategory(id));
    }

    @GetMapping("/get")
    @ApiOperation("获取分类list")
    public CommonResult list(){
        return CommonResult.success(categoryService.getCategoryList(new BlogCategory()));
    }


    @GetMapping("/page")
    @ApiOperation("后台分类分页查询")
    public CommonResult<CommonPage<BlogCategory>> pageQuery(BlogCategory category,
                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<BlogCategory> categories = categoryService.getCategoryList(category,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(categories));
    }


}
