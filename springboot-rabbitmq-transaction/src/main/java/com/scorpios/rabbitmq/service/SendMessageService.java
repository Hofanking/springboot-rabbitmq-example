package com.scorpios.rabbitmq.service;

import com.scorpios.rabbitmq.config.TransactionRabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@Service
public class SendMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 添加事务注解
//    @Transactional
    public void sendMessage(){
        // 设置通信信道为事务模式
//        rabbitTemplate.setChannelTransacted(true);
        // 创建消息对象
        Message message = MessageBuilder.withBody("message transaction ...".getBytes(StandardCharsets.UTF_8))
                .build();
        rabbitTemplate.convertAndSend(TransactionRabbitMQConfig.SCORPIOS_EXCHANGE_NAME, TransactionRabbitMQConfig.SCORPIOS_MSG_QUEUE,message);
        // 模拟异常发生
        int i = 1/0;
    }
}
