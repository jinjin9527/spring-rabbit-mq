package com.sylinx.rabbitmq.order.consumer.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"sms.topic.queue"})
@Service
public class TopicSmsConsumer {
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("sms topic receive : " + message);
    }
}
