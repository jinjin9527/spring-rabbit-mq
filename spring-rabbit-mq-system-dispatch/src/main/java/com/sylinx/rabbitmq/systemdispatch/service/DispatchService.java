package com.sylinx.rabbitmq.systemdispatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class DispatchService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void dispatch(String orderId) throws Exception{
        String sqlString = "insert into sylinx_dispatcher(order_id, dispatch_id, status, order_content, create_time, user_id) values(?, ?, ?, ?, ?, ?)";
        int count = jdbcTemplate.update(sqlString ,orderId, UUID.randomUUID().toString(), "0", "order_content1", new Date(), "user1");

        if (count != 1) {
            throw new Exception("订单创建失败！" + orderId);
        }
    }
}
