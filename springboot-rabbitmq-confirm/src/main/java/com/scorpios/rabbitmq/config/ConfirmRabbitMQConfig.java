package com.scorpios.rabbitmq.config;

import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ConfirmRabbitMQConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback, InitializingBean {

    @Autowired
    RabbitTemplate rabbitTemplate;

    // 交换机的名称
    public static final String SCORPIOS_EXCHANGE_NAME = "scorpios_exchange_name";

    // 发送队列名称
    public static final String SCORPIOS_MSG_QUEUE = "scorpios_msg_queue";

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(ConfirmRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,true,false);
    }

    @Bean
    Queue queue() {
        return new Queue(ConfirmRabbitMQConfig.SCORPIOS_MSG_QUEUE,true,false,false);
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(ConfirmRabbitMQConfig.SCORPIOS_MSG_QUEUE);
    }

    // 为RabbitTemplate绑定回调
    @Override
    public void afterPropertiesSet() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    // 消息到达交换机时回调
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("{}:消息成功到达交换机",correlationData.getId());
        }else{
            log.error("{}:消息发送失败", correlationData.getId());
        }
    }

    // 消息路由到队列失败时回调
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("{}:消息未成功路由到队列",returned.getMessage().getMessageProperties().getMessageId());
    }


}
