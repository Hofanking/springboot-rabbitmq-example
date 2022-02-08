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

    @Bean
    DirectExchange dlxDirectExchange(){
        return new DirectExchange(DLXRabbitMQConfig.SCORPIOS_DLX_EXCHANGE,true,false);
    }

    /**
     * 第一个参数是消息队列的名字
     * 第二个参数表示消息是否持久化
     * 第三个参数表示消息队列是否排他，一般我们都是设置为 false，即不排他
     * 第四个参数表示如果该队列没有任何订阅的消费者的话，该队列会被自动删除，一般适用于临时队列
     * @return
     */
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
