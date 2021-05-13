package com.wmk.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static final String QUEUE_NAME1 = "test.Queue.1";
    public static final String QUEUE_NAME2 = "test.Queue.2";
    public static final String EXCHANGE_NAME = "exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionFactoryUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME1, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME2, true, false, true, null);

        // 声明exchange前先进行清初
        channel.exchangeDelete(EXCHANGE_NAME);

        // 声明exchange
//        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.FANOUT);     //只与绑定有关，与路由键无关
//        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.DIRECT);    //与绑定和路由键都有关
        //*代表过滤一单词，#代表过滤后面所有单词，用.隔开
        channel.exchangeDeclare(EXCHANGE_NAME,ExchangeTypes.TOPIC);      //在DIRECT基础上进行模糊匹配

        //交换机和队列绑定
        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "test.#");
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "test.Queue.*");


        for (int i = 0; i < 1; i++) {
            String message = UUID.randomUUID().toString();
            channel.basicPublish(EXCHANGE_NAME, "test.Queue1.1", null, message.getBytes());
            System.out.println("发送的信息为:" + message);
        }
        channel.close();
        connection.close();

    }
}
