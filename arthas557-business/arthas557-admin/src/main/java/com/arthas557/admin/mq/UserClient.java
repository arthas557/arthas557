package com.arthas557.admin.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface UserClient {

    String INPUT = "userInput";
    String OUTPUT = "userOutput";

    @Input(UserClient.INPUT)
    SubscribableChannel input();

    @Output(UserClient.OUTPUT)
    MessageChannel output();
}
