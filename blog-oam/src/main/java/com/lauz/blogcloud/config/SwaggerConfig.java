package com.lauz.blogcloud.config;

import com.lauz.blogcloud.common.config.BaseSwaggerConfig;
import com.lauz.blogcloud.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.lauz.blogcloud.controller")
                .title("blog后台系统用户资源等服务")
                .description("blog后台oam服务相关接口文档")
                .contactName("lauz")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
