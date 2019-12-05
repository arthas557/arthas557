package com.arthas557.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangty
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

     public static void main(String[] args) {
            //cloud-gateway 依赖的是spring-boot-starter-webflux,此处不能使用WebApplicationType.SERVLET
            new SpringApplicationBuilder(GatewayApplication.class).web(WebApplicationType.REACTIVE).run(args);
     }

}
