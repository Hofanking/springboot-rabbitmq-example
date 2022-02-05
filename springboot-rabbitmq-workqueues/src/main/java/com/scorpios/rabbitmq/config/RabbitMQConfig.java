package com.scorpios.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {


    // 队列的名称
    public static final String SCORPIOS_QUEUE_NAME = "scorpios_queue_name";


    /**
     * 第一个参数是消息队列的名字
     * 第二个参数表示消息是否持久化
     * 第三个参数表示消息队列是否排他，一般我们都是设置为 false，即不排他
     * 第四个参数表示如果该队列没有任何订阅的消费者的话，该队列会被自动删除，一般适用于临时队列
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue(RabbitMQConfig.SCORPIOS_QUEUE_NAME,true,false,false);
    }


}
