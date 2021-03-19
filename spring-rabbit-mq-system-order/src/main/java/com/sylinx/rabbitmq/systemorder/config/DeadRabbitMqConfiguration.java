package com.sylinx.rabbitmq.systemorder.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadRabbitMqConfiguration {

    @Bean
    public FanoutExchange deadExchange(){
        return new FanoutExchange("dead_fanout_order_exchange");
    }

    @Bean
    public Queue deadFanoutQueue(){
        return new Queue("dead.fanout.queue", true);
    }

    @Bean
    public Binding deadDirectBinding(){
        return BindingBuilder.bind(deadFanoutQueue()).to(deadExchange());
    }

    @Bean
    public FanoutExchange orderFanoutExchange(){
        return new FanoutExchange("order_sylinx_exchange");
    }

    @Bean
    public Queue orderFanoutQueue(){
        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", "dead_fanout_order_exchange");
        return new Queue("order_sylinx_queue", true, false, false, map);
    }

    @Bean
    public Binding orderFanoutBinding(){
        return BindingBuilder.bind(orderFanoutQueue()).to(orderFanoutExchange());
    }
}
