package com.wmk.receiveUtil;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class receive {

    @RabbitListener(queues = "testQueue", containerFactory = "simpleRabbitListenerContainerFactory")
    public void get1(Message msg, Channel channel) throws IOException {
        System.out.println("接收到的消息为1："+new String(msg.getBody()));
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        channel.basicAck(msg.getMessageProperties().getDeliveryTag(),true);
        channel.basicNack(msg.getMessageProperties().getDeliveryTag(),true,false);
    }

    @RabbitListener(queues = "deadQueue", containerFactory = "simpleRabbitListenerContainerFactory")
    public void get2(Message msg, Channel channel) throws IOException {
        System.out.println("接收到的消息为2："+new String(msg.getBody()));
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(),true);
        //channel.basicNack(msg.getMessageProperties().getDeliveryTag(),true,true);
    }
}
