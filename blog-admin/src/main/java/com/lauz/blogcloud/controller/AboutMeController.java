package com.lauz.blogcloud.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "关于我api")
@RequestMapping("/about")
public class AboutMeController {
}
