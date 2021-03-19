package com.sylinx.kuangstudy.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = null;
        Channel channel = null;
        Connection connection =null;
        try {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("localhost");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("admin");
            connectionFactory.setPassword("admin123");
            connectionFactory.setVirtualHost("/");
            connection = connectionFactory.newConnection("simpleProducer");
            channel = connection.createChannel();

            /**
             * @param1 队列名称
             * @param2 是否持久化
             * @param3 排他性 是否独占独立
             * @param4 是否自动删除 随着最后一个消费者消息完毕之后是否把队列删除
             * @param5 携带附属参数
             *
             * 持久化队列，则服务器重启不会被删除
             * 问题1：非持久化队列，消息会存盘码？会存盘，但是服务器重启会丢失
             * 问题2：rabbitma为什么是基于channel处理而不是链接？
             *
             */
            String message = "direct test Message!";
            String exchangeName = "direct_exchange";
            String routingKey = "email";
            channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
            System.out.println("direct-exchange sucess!");
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(channel != null&& channel.isOpen()) {
                channel.close();
            }
            if(connection!= null) {
                connection.close();
            }
        }

    }
}
