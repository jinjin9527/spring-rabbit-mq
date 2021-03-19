package com.sylinx.rabbitmq.systemorder.service;

import com.sylinx.rabbitmq.systemorder.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQOrderService {

    @Autowired
    private OrderDataBaseService orderDataBaseService;
    @Autowired
    private RabbitMQService rabbitMQService;

    public void createOrder(Order order) throws Exception {
        orderDataBaseService.saveOrder(order);
        rabbitMQService.sendMessage(order);
    }
}
