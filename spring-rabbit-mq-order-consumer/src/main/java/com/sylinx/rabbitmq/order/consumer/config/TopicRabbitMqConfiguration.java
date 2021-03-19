package com.sylinx.rabbitmq.order.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitMqConfiguration {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic_order_exchange");
    }

    @Bean
    public Queue smsTopicQueue(){
        return new Queue("sms.topic.queue", true);
    }

    @Bean
    public Queue duanxinTopicQueue(){
        return new Queue("duanxin.topic.queue", true);
    }

    @Bean
    public Queue emailTopicQueue(){
        return new Queue("email.topic.queue", true);
    }

    @Bean
    public Binding smsTopicBinding(){
        return BindingBuilder.bind(smsTopicQueue()).to(topicExchange()).with("com.#");
    }

    @Bean
    public Binding duanxinTopicBinding(){
        return BindingBuilder.bind(duanxinTopicQueue()).to(topicExchange()).with("#.duanxin.#");
    }

    @Bean
    public Binding emailTopicBinding(){
        return BindingBuilder.bind(emailTopicQueue()).to(topicExchange()).with("*.email.#");
    }
}
