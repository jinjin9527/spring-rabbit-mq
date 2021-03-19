package com.sylinx.rabbitmq.order.consumer.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"sms.fanout.queue"})
@Service
public class FanoutSmsConsumer {
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("sms fanout receive : " + message);
    }
}
