package com.sylinx.rabbitmq.order.consumer.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"sms.direct.queue"})
@Service
public class DirectSmsConsumer {
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("sms direct receive : " + message);
    }
}
