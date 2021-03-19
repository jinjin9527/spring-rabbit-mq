package com.sylinx.rabbitmq.order.producer.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void makeOrderFanout(String userid, String productid, int num) {
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功 : " + orderId);
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);
    }

    public void makeOrderDirect(String userid, String productid, int num) {
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功 : " + orderId);
        String exchangeName = "direct_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName, "email", orderId);
        rabbitTemplate.convertAndSend(exchangeName, "duanxin", orderId);
    }


    public void makeOrderTopic(String userid, String productid, int num) {
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功 : " + orderId);
        String exchangeName = "topic_order_exchange";
        String routingKey = "com.email.duanxin.test";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);
    }

    public void makeOrderTTL(String userid, String productid, int num) {
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功 : " + orderId);
        String exchangeName = "ttl_direct_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName, "ttl", orderId);
    }

    public void makeOrderTTLMessage(String userid, String productid, int num) {
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功 : " + orderId);
        String exchangeName = "ttl_direct_order_exchange";
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        rabbitTemplate.convertAndSend(exchangeName, "ttl.message", orderId, messagePostProcessor);
    }
}
