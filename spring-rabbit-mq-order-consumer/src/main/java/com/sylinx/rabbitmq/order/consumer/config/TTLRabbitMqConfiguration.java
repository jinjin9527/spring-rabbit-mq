package com.sylinx.rabbitmq.order.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TTLRabbitMqConfiguration {

    @Bean
    public DirectExchange ttlDirectExchange(){
        return new DirectExchange("ttl_direct_order_exchange");
    }

    @Bean
    public Queue ttlDirectQueue(){
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 5000);
        map.put("x-dead-letter-exchange", "dead_direct_order_exchange");
        map.put("x-dead-letter-routing-key", "dead");
        return new Queue("ttl.direct.queue", true, false, false, map);
    }

    @Bean
    public Queue ttlMessageDirectQueue(){
        return new Queue("ttl.message.direct.queue", true);
    }

    @Bean
    public Binding ttlDirectBinding(){
        return BindingBuilder.bind(ttlDirectQueue()).to(ttlDirectExchange()).with("ttl");
    }
    @Bean
    public Binding ttlMessageDirectBinding(){
        return BindingBuilder.bind(ttlMessageDirectQueue()).to(ttlDirectExchange()).with("ttl.message");
    }
}
