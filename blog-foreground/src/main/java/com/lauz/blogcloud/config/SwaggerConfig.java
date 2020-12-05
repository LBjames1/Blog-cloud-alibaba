package com.lauz.blogcloud.config;

import com.lauz.blogcloud.common.config.BaseSwaggerConfig;
import com.lauz.blogcloud.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: Blog-cloud-alibaba
 * @description: swagger配置
 * @author: lauz
 * @create: 2020-12-05 21:45
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.lauz.blogcloud.controller")
                .title("blog前端接口")
                .description("blog前端页面相关接口文档")
                .contactName("lauz")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
