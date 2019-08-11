package com.rabbitmq.rabbitmq_demo.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * className: Consumer
 * description: TODO
 *
 * @author hasee
 * @version 1.0
 * @date 2019/8/11 13:27
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("101.132.43.140");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        //通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        //通过connection创建一个Channel
        Channel channel = connection.createChannel();
        //创建队列
        String queueName = "test001";
        channel.queueDeclare(queueName, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        //以对象的形式提供一个回调，它将缓冲消息
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

    }
}
