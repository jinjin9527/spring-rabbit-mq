package com.sylinx.rabbitmq.order.consumer.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"email.fanout.queue"})
@Service
public class FanoutEmailConsumer {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("email fanout receive : " + message);
    }
}
