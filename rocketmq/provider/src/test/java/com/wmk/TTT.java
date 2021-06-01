package com.wmk;

import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/6/1
 * @TIME: 20:37
 **/
public class TTT {
    public static void main(String[] args) {
        Message msg = MessageBuilder.withPayload("Hello RocketMQ ").
                setHeader(RocketMQHeaders.KEYS, "KEY_").build();
        System.out.println(msg);
    }
}
