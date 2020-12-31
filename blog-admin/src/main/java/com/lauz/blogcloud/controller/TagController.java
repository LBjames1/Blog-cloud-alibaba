package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonPage;
import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.model.BlogCategory;
import com.lauz.blogcloud.model.BlogTag;
import com.lauz.blogcloud.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "文章标签api")
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/add")
    @ApiOperation("添加标签")
    public CommonResult add(@RequestBody BlogTag tag){
        int result = tagService.save(tag);
        if(result>0){
            return CommonResult.success("添加成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update")
    @ApiOperation("更新标签")
    public CommonResult update(@RequestBody BlogTag tag){
        int result = tagService.update(tag);
        if(result>0){
            return CommonResult.success("更新成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("删除标签")
    public CommonResult del(@PathVariable("id") Integer id){
        int result = tagService.delete(id);
        if(result>0){
            return CommonResult.success("删除成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/get/{id}")
    @ApiOperation("根据id获取标签")
    public CommonResult get(@PathVariable("id") Integer id){
        return CommonResult.success(tagService.getTag(id));
    }

    @GetMapping("/get")
    @ApiOperation("获取标签list")
    public CommonResult list(){
        return CommonResult.success(tagService.getTagList());
    }


    @GetMapping("/page")
    @ApiOperation("后台标签分页查询")
    public CommonResult<CommonPage<BlogTag>> pageQuery(BlogTag tag,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<BlogTag> tags = tagService.getTagList(tag,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(tags));
    }
}
