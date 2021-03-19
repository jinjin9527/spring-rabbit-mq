package com.sylinx.rabbitmq.systemorder.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sylinx.rabbitmq.systemorder.model.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RabbitMQService{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void regCallback(){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("rabbitmq cause : " + cause);
                String orderId = correlationData.getId();
                if(!ack) {
                    System.out.println("rabbitmq 应答失败 orderId : " + orderId);
                    return;
                }
                try{
                    String updateString = " update sylinx_order_message set status ='1' where order_id = ?";
                    int count = jdbcTemplate.update(updateString, orderId);
                    if (count == 1) {
                        System.out.println("消息投递成功 : " + orderId);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("消息状态更改时db异常");
                }
            }
        });
    }

    public void sendMessage(Order order) throws JsonProcessingException {
        String orderJson = new ObjectMapper().writeValueAsString(order);
        rabbitTemplate.convertAndSend("order_sylinx_exchange", "", orderJson, new CorrelationData(order.getOrderId()));
    }
}
