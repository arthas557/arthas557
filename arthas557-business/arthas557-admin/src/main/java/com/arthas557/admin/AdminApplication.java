package com.arthas557.admin;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.arthas557")
public class AdminApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
