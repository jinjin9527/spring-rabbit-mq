package com.sylinx.rabbitmq.order.consumer.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"email.topic.queue"})
@Service
public class TopicEmailConsumer {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("email topic receive : " + message);
    }
}
