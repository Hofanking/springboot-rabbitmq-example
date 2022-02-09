package com.scorpios.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PluginRabbitMQConfig {

    // 交换机的名称
    public static final String SCORPIOS_EXCHANGE_NAME = "scorpios_exchange_name";

    // 队列名称
    public static final String SCORPIOS_MSG_QUEUE = "scorpios_msg_queue";

    // 交换机类型
    public static final String SCORPIOS_EXCHANGE_TYPE = "x-delayed-message";

    @Bean
    CustomExchange customExchange(){
        Map<String, Object> setting = new HashMap<>();
        setting.put("x-delayed-type", "direct");
        return new CustomExchange(PluginRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,PluginRabbitMQConfig.SCORPIOS_EXCHANGE_TYPE,true,false, setting);
    }

    @Bean
    Queue queue() {
        return new Queue(PluginRabbitMQConfig.SCORPIOS_MSG_QUEUE,true,false,false);
    }

    @Bean
    Binding bindingMsg(){
        return BindingBuilder.bind(queue()).to(customExchange()).with(PluginRabbitMQConfig.SCORPIOS_MSG_QUEUE).noargs();
    }

}
