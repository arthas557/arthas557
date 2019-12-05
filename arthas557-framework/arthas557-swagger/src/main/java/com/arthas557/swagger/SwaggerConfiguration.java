//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.arthas557.swagger;

import com.alibaba.nacos.client.config.NacosConfigService;
import com.google.common.base.Predicate;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfiguration implements WebMvcConfigurer {
    //暂时不处理包扫描
    @Value("${swagger.createName}")
    private String createName;
    @Value("${swagger.serviceName}")
    private String serviceName ;
    @Value("${swagger.description}")
    private String description ;
    @Value("${swagger.startFlag}")
    private String startFlag ;


    public SwaggerConfiguration() {
    }

    /*配置 swagger-ui 内静态资源*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html/*").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/v2/api-docs")
                ;
    }



    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("auth-name")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tokenPar.build());
        return (new Docket(DocumentationType.SWAGGER_2))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //todo 线上禁用swagger
                .paths(this.swaggerPath())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder())
                .title(this.serviceName + " Restful APIs")
                .description(this.description)
                .contact(this.createName)
                .version("1.0")
                .build();
    }

    /**
     *
     */
    private Predicate<String> swaggerPath(){
        Predicate<String> paths;
        if("true".equals(startFlag)){
            paths = PathSelectors.any();
        }else {
            paths = PathSelectors.none();
        }
        log.info("当前环境swagger开启标识:{}", startFlag);
        return paths;
    }
}
