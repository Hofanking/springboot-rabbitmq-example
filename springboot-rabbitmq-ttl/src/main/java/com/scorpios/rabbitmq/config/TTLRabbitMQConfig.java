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
public class TTLRabbitMQConfig {


    // 交换机的名称
    public static final String SCORPIOS_TTL_EXCHANGE_NAME = "scorpios_ttl_exchange_name";


    // 发送队列名称
    public static final String SCORPIOS_TTL_MSG_QUEUE = "scorpios_ttl_msg_queue";

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(TTLRabbitMQConfig.SCORPIOS_TTL_EXCHANGE_NAME,true,false);
    }

    /**
     * 第一个参数是消息队列的名字
     * 第二个参数表示消息是否持久化
     * 第三个参数表示消息队列是否排他，一般我们都是设置为 false，即不排他
     * 第四个参数表示如果该队列没有任何订阅的消费者的话，该队列会被自动删除，一般适用于临时队列
     * @return
     */
//    @Bean
//    Queue queue() {
//        return new Queue(TTLRabbitMQConfig.SCORPIOS_TTL_MSG_QUEUE,true,false,false);
//    }


    /**
     * 第一个参数是消息队列的名字
     * 第二个参数表示消息是否持久化
     * 第三个参数表示消息队列是否排他，一般我们都是设置为 false，即不排他
     * 第四个参数表示如果该队列没有任何订阅的消费者的话，该队列会被自动删除，一般适用于临时队列
     * 第五个参数表示给队列设置参数
     * @return
     */
    @Bean
    Queue queue() {
        Map<String, Object> setting = new HashMap<>();
        setting.put("x-message-ttl", 20000);
        return new Queue(TTLRabbitMQConfig.SCORPIOS_TTL_MSG_QUEUE,true,false,false,setting);
    }


    @Bean
    Binding bindingMsg(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(TTLRabbitMQConfig.SCORPIOS_TTL_MSG_QUEUE);
    }

}
