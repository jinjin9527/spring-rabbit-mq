package com.sylinx.rabbitmq.order.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitMqConfiguration {

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_order_exchange");
    }

    @Bean
    public Queue smsFanoutQueue(){
        return new Queue("sms.fanout.queue", true);
    }

    @Bean
    public Queue duanxinFanoutQueue(){
        return new Queue("duanxin.fanout.queue", true);
    }

    @Bean
    public Queue emailFanoutQueue(){
        return new Queue("email.fanout.queue", true);
    }

    @Bean
    public Binding smsFanoutBinding(){
        return BindingBuilder.bind(smsFanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding duanxinFanoutBinding(){
        return BindingBuilder.bind(duanxinFanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailFanoutBinding(){
        return BindingBuilder.bind(emailFanoutQueue()).to(fanoutExchange());
    }
}
