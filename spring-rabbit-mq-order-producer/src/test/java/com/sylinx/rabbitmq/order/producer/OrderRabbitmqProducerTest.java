package com.sylinx.rabbitmq.order.producer;

import com.sylinx.rabbitmq.order.producer.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderRabbitmqProducerTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testFanout(){
        orderService.makeOrderFanout("u1", "p1", 1);
    }

    @Test
    public void testDirect(){
        orderService.makeOrderDirect("u2", "p2", 2);
    }

    @Test
    public void testTopic(){
        orderService.makeOrderTopic("u3", "p3", 3);
    }

    @Test
    public void testTTL(){
        orderService.makeOrderTTL("u3", "p3", 3);
    }

    @Test
    public void testTTLMessage(){
        orderService.makeOrderTTLMessage("u3", "p3", 3);
    }
}
