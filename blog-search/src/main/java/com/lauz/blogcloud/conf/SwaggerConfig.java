package com.lauz.blogcloud.conf;

import com.lauz.blogcloud.common.config.BaseSwaggerConfig;
import com.lauz.blogcloud.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: Blog-cloud-alibaba
 * @description: swagger配置
 * @author: lauz
 * @create: 2020年12月18日15:24
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.lauz.blogcloud.controller")
                .title("blog前端搜索接口集成es")
                .description("blog前端搜索")
                .contactName("lauz")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
