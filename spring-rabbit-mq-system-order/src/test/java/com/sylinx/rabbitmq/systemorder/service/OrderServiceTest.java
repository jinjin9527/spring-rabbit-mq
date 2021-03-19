package com.sylinx.rabbitmq.systemorder.service;

import com.sylinx.rabbitmq.systemorder.model.Order;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MQOrderService mqOrderService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    private void setUp() {
        jdbcTemplate.execute("delete from sylinx_dispatcher");
        jdbcTemplate.execute("delete from sylinx_order");
        jdbcTemplate.execute("delete from sylinx_order_message");
    }

    @Test
    public void testOrderService1() throws Exception {
        String orderId = "1";
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderContent("orderContent1");
        order.setUserId("user1");
        order.setCreateTime(new Date());
        orderService.createOrder(order);
        System.out.println("test1 success!");
    }

    @Test
    public void testOrderService2() throws Exception {
        String orderId = "1001";
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderContent("orderContent2");
        order.setUserId("user2");
        order.setCreateTime(new Date());
        orderService.createOrder(order);
        System.out.println("test2 success!");
    }

    @Test
    public void testRabbitMQService1() throws Exception {
        setUp();
        String orderId = "4";
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderContent("orderContent3");
        order.setUserId("user3");
        order.setCreateTime(new Date());
        mqOrderService.createOrder(order);
        System.out.println("test1 success!");
    }
}
