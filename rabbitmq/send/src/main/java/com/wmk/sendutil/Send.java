package com.wmk.sendutil;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class Send {
    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    AmqpAdmin amqpAdmin;      //接口:AMQP规范中指定了如何配置queues, exchanges, and bindings

    @Resource
    RabbitAdmin rabbitAdmin;  //RabbitMQ 是 AmqpAdmin 的实现类，两处注入的是同一个对象

    public void sendMessage(String exchangeName,String routingKey,String msg){
        System.out.println("发送的消息为："+msg);
        rabbitTemplate.convertAndSend(exchangeName,routingKey,msg,new CorrelationData("消息ID"));
    }

    public void creatDirectExchange(String exchangeName){
        amqpAdmin.declareExchange(new DirectExchange(exchangeName));
    }

    public void creatQueue(String queueName){
        amqpAdmin.declareQueue(new Queue(queueName,false));
    }

    public void creatBind(String queueName,String exchangeName,String routingKey){
        amqpAdmin.declareBinding(new Binding(queueName, Binding.DestinationType.QUEUE,exchangeName,routingKey,null));
    }
}
