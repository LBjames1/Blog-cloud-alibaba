package com.lauz.blogcloud.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.lauz.blogcloud.mapper","com.lauz.blogcloud.dao"})
public class MyBatisConfig {
}
