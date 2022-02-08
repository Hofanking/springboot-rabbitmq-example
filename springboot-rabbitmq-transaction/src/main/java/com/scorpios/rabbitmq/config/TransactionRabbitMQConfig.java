package com.scorpios.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TransactionRabbitMQConfig {

    // 交换机的名称
    public static final String SCORPIOS_EXCHANGE_NAME = "scorpios_exchange_name";

    // 发送队列名称
    public static final String SCORPIOS_MSG_QUEUE = "scorpios_msg_queue";

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(TransactionRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,true,false);
    }

    @Bean
    Queue queue() {
        return new Queue(TransactionRabbitMQConfig.SCORPIOS_MSG_QUEUE,true,false,false);
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(TransactionRabbitMQConfig.SCORPIOS_MSG_QUEUE);
    }

    @Bean
    RabbitTransactionManager transactionManager(ConnectionFactory connectionFactory){
        return new RabbitTransactionManager(connectionFactory);
    }

}
