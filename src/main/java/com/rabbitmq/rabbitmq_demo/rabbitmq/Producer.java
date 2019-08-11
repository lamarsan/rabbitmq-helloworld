package com.rabbitmq.rabbitmq_demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * className: Producer
 * description: TODO
 *
 * @author hasee
 * @version 1.0
 * @date 2019/8/11 13:24
 */
public class Producer {
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
        //发送数据
        String msg = "Hello!!!";
        //The default exchange is implicitly bound to every queue, with a routing key equal to the queue name.
        //It is not possible to explicitly bind to, or unbind from the default exchange. It also cannot be deleted.
        channel.basicPublish("", "test001", null, msg.getBytes());
        System.out.println("Sent '"+msg+ "'");
        //关闭连接
        channel.close();
        connection.close();
    }
}
