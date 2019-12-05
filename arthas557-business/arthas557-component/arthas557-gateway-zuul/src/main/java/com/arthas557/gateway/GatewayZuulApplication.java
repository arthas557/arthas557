package com.arthas557.gateway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @author wangty
 */
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class GatewayZuulApplication {

     public static void main(String[] args) {
            new SpringApplicationBuilder(GatewayZuulApplication.class).run(args);
     }

}
