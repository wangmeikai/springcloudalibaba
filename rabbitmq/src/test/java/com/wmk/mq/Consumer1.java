package com.wmk.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Consumer1 {
    public static final String QUEUE_NAME = "test.Queue.1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionFactoryUtils.getConnection();
        Channel channel = connection.createChannel();
        DefaultConsumer deliverCallback = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接收消息为："+new String(body, "UTF-8"));

                //envelope.getDeliveryTag()消息的唯一表识，类似id，有rabbitmq内部维护
                System.out.println(envelope.getDeliveryTag());

                //手动确认消息被消费
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE_NAME, false, deliverCallback);
    }
}