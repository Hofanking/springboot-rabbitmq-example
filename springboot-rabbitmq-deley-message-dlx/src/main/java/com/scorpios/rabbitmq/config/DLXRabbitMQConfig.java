package com.scorpios.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DLXRabbitMQConfig {

    // 交换机的名称
    public static final String SCORPIOS_DLX_EXCHANGE = "scorpios_dlx_exchange";

    // 发送队列名称
    public static final String SCORPIOS_DLX_QUEUE = "scorpios_dlx_queue";

    public static final String SCORPIOS_MSG_QUEUE = "scorpios_msg_queue";

    // 死信交换机
    @Bean
    DirectExchange dlxDirectExchange(){
        return new DirectExchange(DLXRabbitMQConfig.SCORPIOS_DLX_EXCHANGE,true,false);
    }

    // 死信队列
    @Bean
    Queue dlxQueue() {
        return new Queue(DLXRabbitMQConfig.SCORPIOS_DLX_QUEUE,true,false,false);
    }

    // 将死信队列和死信交换机绑定
    @Bean
    Binding dlxBinding(){
        return BindingBuilder.bind(dlxQueue()).to(dlxDirectExchange()).with(DLXRabbitMQConfig.SCORPIOS_DLX_QUEUE);
    }

    // 创建一个普通队列，并把它与死信交换机
    @Bean
    Queue msgQueue() {
        Map<String, Object> setting = new HashMap<>();
        // 设置死信交换机
        setting.put("x-dead-letter-exchange", DLXRabbitMQConfig.SCORPIOS_DLX_EXCHANGE);
        // 设置死信 routing_key 与队列名称相同
        setting.put("x-dead-letter-routing-key", DLXRabbitMQConfig.SCORPIOS_DLX_QUEUE);
        return new Queue(DLXRabbitMQConfig.SCORPIOS_MSG_QUEUE, true, false, false, setting);
    }
}
