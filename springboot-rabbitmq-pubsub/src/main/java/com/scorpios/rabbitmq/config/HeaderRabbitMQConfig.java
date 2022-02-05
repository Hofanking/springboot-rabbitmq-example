package com.scorpios.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HeaderRabbitMQConfig {

    // 交换机的名称
    public static final String SCORPIOS_EXCHANGE_NAME = "scorpios_exchange_name";

    // 队列的名称
    public static final String SCORPIOS_QUEUE_NAME_ONE = "scorpios_queue_name_name";
    public static final String SCORPIOS_QUEUE_NAME_TWO = "scorpios_queue_name_age";

    /**
     * 创建一个HeadersExchange交换机
     * 第一个参数：交换机名字
     * 第二个参数：重启后是否依然有效
     * 第三个参数：长期未用时是否删除
     * @return
     */
    @Bean
    HeadersExchange headersExchange(){
        return new HeadersExchange(HeaderRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,true,false);
    }

    @Bean
    Queue queueName() {
        return new Queue(HeaderRabbitMQConfig.SCORPIOS_QUEUE_NAME_ONE,true,false,false);
    }

    @Bean
    Queue queueAge() {
        return new Queue(HeaderRabbitMQConfig.SCORPIOS_QUEUE_NAME_TWO,true,false,false);
    }

    // 将队列与HeadersExchange绑定
    @Bean
    Binding bindingName() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "scorpios");
        return BindingBuilder.bind(queueName()).to(headersExchange()).whereAny(map).match();
    }

    @Bean
    Binding bindingAge() {
        return BindingBuilder.bind(queueAge()).to(headersExchange()).whereAny("age").exist();
    }

}
