package com.arthas557.admin.mq;

import com.alibaba.fastjson.JSON;
import com.arthas557.admin.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(UserClient.class)
public class UserListener {

    @StreamListener(UserClient.OUTPUT)
    public void receive(User user) {
        log.info("StreamReceiver: {}", JSON.toJSONString(user));
        System.out.println("User receiver"+JSON.toJSONString(user));
    }

}
