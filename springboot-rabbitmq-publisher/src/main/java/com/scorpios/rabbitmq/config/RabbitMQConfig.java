package com.scorpios.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    // 交换机的名称
    public static final String SCORPIOS_EXCHANGE_NAME = "scorpios_exchange_name";


    // 队列的名称
    public static final String SCORPIOS_QUEUE_NAME = "scorpios_queue_name";

//    @Bean
//    Exchange directExchange(){
//        return new DirectExchange(RabbitMQConfig.SCORPIOS_EXCHANGE_NAME,true,false);
//    }

    @Bean
    Queue queue() {
        return new Queue(RabbitMQConfig.SCORPIOS_QUEUE_NAME,true,false,false);
    }

//    @Bean
//    BindingBuilder.GenericArgumentsConfigurer binding() {
//        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
//    }

//    @Bean
////    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
////       RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
////       rabbitTemplate.setUsePublisherConnection(true);
////       return rabbitTemplate;
////    }

}
