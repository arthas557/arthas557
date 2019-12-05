package com.arthas557.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    private CustomerExceptionResovler customerExceptionResovler;
    @Bean
    public CustomerExceptionResovler customerExceptionResovler(){
        return new CustomerExceptionResovler();
    }

    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(customerExceptionResovler);
        super.configureHandlerExceptionResolvers(exceptionResolvers);
    }
}
