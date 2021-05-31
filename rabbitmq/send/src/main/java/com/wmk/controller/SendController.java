package com.wmk.controller;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
public class SendController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send(){
        rabbitTemplate.convertAndSend("directExchange",
                "direct.key",
                UUID.randomUUID().toString(),
                new CorrelationData("消息ID"));
        return "ok";
    }
}
