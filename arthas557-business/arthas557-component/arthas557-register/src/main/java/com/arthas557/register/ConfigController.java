package com.arthas557.register;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@Controller
@RequestMapping("/config")
@RefreshScope
@RestController
public class ConfigController {

    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @RequestMapping("/test")
    public String test(){
        String x = username + "/" + password+"/";
        System.out.println(x);
        return x;
    }

    public static void main(String[] args) {
        try {
            String serverAddr = "47.111.12.202:8848";
            String dataId = "register.yaml";
            String group = "DEFAULT_GROUP";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
