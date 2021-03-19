package com.sylinx.rabbitmq.order.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadRabbitMqConfiguration {

    @Bean
    public DirectExchange deadExchange(){
        return new DirectExchange("dead_direct_order_exchange");
    }

    @Bean
    public Queue deadDirectQueue(){
        return new Queue("dead.direct.queue", true);
    }

    @Bean
    public Binding deadDirectBinding(){
        return BindingBuilder.bind(deadDirectQueue()).to(deadExchange()).with("dead");
    }
}
