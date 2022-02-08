package com.scorpios.rabbitmq.controller;


import com.scorpios.rabbitmq.config.TTLRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @GetMapping("/send/message")
//    public String send() {
//
//        // 创建消息对象
//        Message message = MessageBuilder.withBody("message set ttl ...".getBytes(StandardCharsets.UTF_8))
//                .setExpiration("20000")
//                .build();
//        rabbitTemplate.convertAndSend(TTLRabbitMQConfig.SCORPIOS_TTL_EXCHANGE_NAME,TTLRabbitMQConfig.SCORPIOS_TTL_MSG_QUEUE,message);
//        return "success";
//    }


    @GetMapping("/send/message")
    public String send() {

        // 创建消息对象
        Message message = MessageBuilder.withBody("message set ttl ...".getBytes(StandardCharsets.UTF_8))
                .build();
        rabbitTemplate.convertAndSend(TTLRabbitMQConfig.SCORPIOS_TTL_EXCHANGE_NAME,TTLRabbitMQConfig.SCORPIOS_TTL_MSG_QUEUE,message);
        return "success";
    }
}
