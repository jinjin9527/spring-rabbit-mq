package com.sylinx.rabbitmq.order.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitMqConfiguration {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_order_exchange");
    }

    @Bean
    public Queue smsDirectQueue(){
        return new Queue("sms.direct.queue", true);
    }

    @Bean
    public Queue duanxinDirectQueue(){
        return new Queue("duanxin.direct.queue", true);
    }

    @Bean
    public Queue emailDirectQueue(){
        return new Queue("email.direct.queue", true);
    }

    @Bean
    public Binding smsDirectBinding(){
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("sms");
    }

    @Bean
    public Binding duanxinDirectBinding(){
        return BindingBuilder.bind(duanxinDirectQueue()).to(directExchange()).with("duanxin");
    }

    @Bean
    public Binding emailDirectBinding(){
        return BindingBuilder.bind(emailDirectQueue()).to(directExchange()).with("email");
    }
}
