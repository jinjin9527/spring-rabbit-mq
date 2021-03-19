package com.sylinx.rabbitmq.order.consumer.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"duanxin.fanout.queue"})
@Service
public class FanoutDuanxinConsumer {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("duanxin fanout receive : " + message);
    }
}
