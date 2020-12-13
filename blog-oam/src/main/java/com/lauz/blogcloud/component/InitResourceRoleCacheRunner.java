package com.lauz.blogcloud.component;

import com.lauz.blogcloud.service.RoleResourceRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @program: Blog-cloud-alibaba
 * @description: 资源角色初始化
 * @author: lauz
 * @create: 2020-12-12 13:35
 * CommandLineRunner与ApplicationRunner参数有区别
 * 都用于springApplication.run之前初始化一些资源
 **/
@Component
@Slf4j
public class InitResourceRoleCacheRunner implements CommandLineRunner {

    @Autowired
    private RoleResourceRelationService relationService;

    @Override
    public void run(String... args) throws Exception {
        log.info("=============RoleResource  init=============");
        relationService.initRoleResourceMap();
    }
}
