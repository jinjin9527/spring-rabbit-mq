package com.sylinx.rabbitmq.order.consumer.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"duanxin.topic.queue"})
@Service
public class TopicDuanxinConsumer {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("duanxin topic receive : " + message);
    }
}
