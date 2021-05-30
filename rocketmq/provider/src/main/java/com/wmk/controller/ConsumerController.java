package com.wmk.controller;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class ConsumerController {

    @Value("${tl.rocketmq.topic}")
    private String springTopic;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/consumer")
    public SendResult index(){
        SendResult result = rocketMQTemplate.syncSend(springTopic,"发送消息");
        return result;
        //return "send_status:"+result.getSendStatus().name();
    }

}
