package com.arthas557.admin;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients("com.arthas557")
@ComponentScan(basePackages = "com.arthas557")
@SpringBootApplication
public class AdminCopyApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminCopyApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}

