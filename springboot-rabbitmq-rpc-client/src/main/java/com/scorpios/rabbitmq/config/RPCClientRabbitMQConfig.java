package com.scorpios.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RPCClientRabbitMQConfig {


    // 交换机的名称
    public static final String SCORPIOS_RPC_EXCHANGE_NAME = "scorpios_rpc_exchange_name";


    // 发送队列名称
    public static final String SCORPIOS_RPC_MSG_QUEUE = "scorpios_rpc_msg_queue";

    // 返回队列名称
    public static final String SCORPIOS_RPC_REPLY_QUEUE = "scorpios_rpc_reply_queue";

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(RPCClientRabbitMQConfig.SCORPIOS_RPC_EXCHANGE_NAME,true,false);
    }

    @Bean
    Queue queueOne() {
        return new Queue(RPCClientRabbitMQConfig.SCORPIOS_RPC_MSG_QUEUE,true,false,false);
    }

    @Bean
    Queue queueTwo() {
        return new Queue(RPCClientRabbitMQConfig.SCORPIOS_RPC_REPLY_QUEUE,true,false,false);
    }


    @Bean
    Binding bindingMsg(){
        return BindingBuilder.bind(queueOne()).to(topicExchange()).with(RPCClientRabbitMQConfig.SCORPIOS_RPC_MSG_QUEUE);
    }

    @Bean
    Binding bindingReply(){
        return BindingBuilder.bind(queueTwo()).to(topicExchange()).with(RPCClientRabbitMQConfig.SCORPIOS_RPC_REPLY_QUEUE);
    }


    /**
     * 自定义 RabbitTemplate发送和接收消息，因为要设置回调队列地址
     */
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
       RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
       rabbitTemplate.setReplyAddress(RPCClientRabbitMQConfig.SCORPIOS_RPC_REPLY_QUEUE);
       rabbitTemplate.setReplyTimeout(5000);
       return rabbitTemplate;
    }

    /**
     * 给返回队列设置监听器
     */
    @Bean
    SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RPCClientRabbitMQConfig.SCORPIOS_RPC_REPLY_QUEUE);
        container.setMessageListener(rabbitTemplate(connectionFactory));
        return container;
    }

}
