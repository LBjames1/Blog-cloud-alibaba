package com.lauz.blogcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatawayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatawayApplication.class,args);
    }
}
