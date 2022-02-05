package com.scorpios.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RPCServerRabbitMQConfig {


    // 交换机的名称
    public static final String SCORPIOS_RPC_EXCHANGE_NAME = "scorpios_rpc_exchange_name";


    // 发送队列名称
    public static final String SCORPIOS_RPC_MSG_QUEUE = "scorpios_rpc_msg_queue";

    // 返回队列名称
    public static final String SCORPIOS_RPC_REPLY_QUEUE = "scorpios_rpc_reply_queue";

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(RPCServerRabbitMQConfig.SCORPIOS_RPC_EXCHANGE_NAME,true,false);
    }

    @Bean
    Queue queueOne() {
        return new Queue(RPCServerRabbitMQConfig.SCORPIOS_RPC_MSG_QUEUE,true,false,false);
    }

    @Bean
    Queue queueTwo() {
        return new Queue(RPCServerRabbitMQConfig.SCORPIOS_RPC_REPLY_QUEUE,true,false,false);
    }


    @Bean
    Binding bindingMsg(){
        return BindingBuilder.bind(queueOne()).to(topicExchange()).with(RPCServerRabbitMQConfig.SCORPIOS_RPC_MSG_QUEUE);
    }

    @Bean
    Binding bindingReply(){
        return BindingBuilder.bind(queueTwo()).to(topicExchange()).with(RPCServerRabbitMQConfig.SCORPIOS_RPC_REPLY_QUEUE);
    }

}
