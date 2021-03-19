package com.sylinx.rabbitmq.systemdispatch.service;

import com.rabbitmq.client.Channel;
import com.sylinx.rabbitmq.systemdispatch.model.Order;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class DeadConsumerService {

    @Autowired
    private DispatchService dispatchService;

    // 消息处理失败，没有处理则会引起服务器无限重发
    // 1. 控制重发次数
    // 2. try catch 手动ack
    // 3. try catch 手动ack 死信队列 人工干预(推荐)
    @RabbitListener(queues = {"dead.fanout.queue"})
    public void messageConsumer(String orderMsg, Channel channel,
            CorrelationData correlationData, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        try {
            System.out.println("收到死信队列mq消息 : " + orderMsg );
            Order order = new ObjectMapper().readValue(orderMsg, Order.class);
            dispatchService.dispatch(order.getOrderId());
            channel.basicAck(tag, false);
        } catch(Exception e) {
            // 死信队列仍然出异常
            // 人工干预
            // 短信预警
            // 保存至db
            // 参数1 消息tag 参数2 多条处理 参数3 重发
            channel.basicNack(tag, false, false);
        }

    }
}
