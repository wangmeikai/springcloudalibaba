package com.wmk.config;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class rabbitmqConfig {

    @Resource
    AmqpAdmin amqpAdmin;      //接口:AMQP规范中指定了如何配置queues, exchanges, and bindings

    @Resource
    RabbitAdmin rabbitAdmin;  //RabbitMQ 是 AmqpAdmin 的实现类，两处注入的是同一个对象


    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("121.196.105.239",5672);
        connectionFactory.setUsername("wmk");
        connectionFactory.setPassword("wmk");
        connectionFactory.setVirtualHost("wmkHost");
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);  //发送方确认（是否到达交换机）
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.print("发送方确认：");
                System.out.print(correlationData.getId()+" ");
                System.out.print(b+" ");
                System.out.println(s);
            }
        });

        rabbitTemplate.setMandatory(true);      //开启失败回调(已经到达交换机，但未到达队列)
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("失败回调：");
                System.out.println(message);
                System.out.println(replyCode);
                System.out.println(replyText);
                System.out.println(exchange);
                System.out.println(routingKey);
            }
        });

        rabbitTemplate.setMessageConverter(new MessageConverter() {
            @Override
            public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
                messageProperties.setContentType("text/plain");
                messageProperties.setContentEncoding("UTF-8");
                Message message = new Message(JSON.toJSONBytes(object), messageProperties);
                return message;
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return message.getBody();
            }
        });

        return rabbitTemplate;
    }


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange("deadExchange");
    }

    @Bean
    public Queue queue() {
        //名字 是否持久化
        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange","deadExchange");
        map.put("x-dead-letter-routing-key","dead.key");
        return new Queue("testQueue", true,false,false,map);
    }

    @Bean
    public Queue deadQueue() {
        //名字 是否持久化
        return new Queue("deadQueue", true);
    }

    @Bean
    public Binding deadBinding(Queue deadQueue, DirectExchange deadExchange) {
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        //Binding binding = new Binding();
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("dead.key");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange) {
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        //Binding binding = new Binding();
        return BindingBuilder.bind(queue).to(directExchange).with("direct.key");
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

