package com.sylinx.rabbitmq.systemorder.service;

import com.sylinx.rabbitmq.systemorder.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderDataBaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void saveOrder(Order order) throws Exception {
        String sqlString ="insert into sylinx_order(order_id, user_id, order_content, create_time) values(?,?,?,?)";
        int count = jdbcTemplate.update(sqlString, order.getOrderId(), order.getUserId(), order.getOrderContent(), order.getCreateTime());

        if (count !=1) {
            throw new Exception("订单创建失败" + order);
        }
        saveLocalMessage(order);
    }

    public void saveLocalMessage(Order order) throws Exception{
        String sqlString = "insert into sylinx_order_message(order_id, order_content, status, unique_id) values(?,?,?,?)";
        int count = jdbcTemplate.update(sqlString, order.getOrderId(), order.getOrderContent(), "0", "1");
        if(count != 1) {
            throw new Exception("消息备份失败!" + order);
        }
    }
}
