package com.scorpios.rabbitmq.controller;


import com.scorpios.rabbitmq.config.DLXRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/message")
    public String send() {
        log.info("客户端发送消息");
        // 创建消息对象
        Message message = MessageBuilder.withBody("message set ttl ...".getBytes(StandardCharsets.UTF_8))
                .setExpiration("5000")
                .build();
        rabbitTemplate.convertAndSend(DLXRabbitMQConfig.SCORPIOS_MSG_QUEUE,message);
        return "success";
    }

}
