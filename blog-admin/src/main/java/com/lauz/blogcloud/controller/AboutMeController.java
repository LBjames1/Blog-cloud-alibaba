package com.lauz.blogcloud.controller;

import com.lauz.blogcloud.common.api.CommonResult;
import com.lauz.blogcloud.model.BlogAboutInfo;
import com.lauz.blogcloud.service.AboutMeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "关于我api")
@RequestMapping("/about")
public class AboutMeController {

    @Autowired
    private AboutMeService aboutMeService;

    @PostMapping("/add")
    @ApiOperation("添加关于我信息")
    public CommonResult add(@RequestBody BlogAboutInfo aboutInfo){
        int result = aboutMeService.add(aboutInfo);
        if(result>0){
            return CommonResult.success("添加成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @PostMapping("/update")
    @ApiOperation("更新关于我信息")
    public CommonResult update(@RequestBody BlogAboutInfo aboutInfo){
        int result = aboutMeService.update(aboutInfo);
        if(result>0){
            return CommonResult.success("更新成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("删除关于我信息")
    public CommonResult del(@PathVariable("id") Integer id){
        int result = aboutMeService.delete(id);
        if(result>0){
            return CommonResult.success("删除成功","success");
        }else{
            return CommonResult.failed();
        }
    }

    @GetMapping("/get")
    @ApiOperation("关于我信息查询")
    public CommonResult get(){
        return CommonResult.success(aboutMeService.getAboutInfo());

    }
}
