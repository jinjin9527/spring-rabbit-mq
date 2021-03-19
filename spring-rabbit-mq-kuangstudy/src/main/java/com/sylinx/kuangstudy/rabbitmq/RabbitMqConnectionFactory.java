package com.sylinx.kuangstudy.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqConnectionFactory {

    public static Connection getRabbitConnectionFactory(String connectionName) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin123");
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection(connectionName);
        return connection;
    }
}
