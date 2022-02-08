package com.scorpios.rabbitmq.controller;


import com.scorpios.rabbitmq.config.ConfirmRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@RestController
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/exchange")
    public String sendExchange() {
        String uuid = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend("TestExchange", ConfirmRabbitMQConfig.SCORPIOS_MSG_QUEUE,"message confirm callback ...".getBytes(StandardCharsets.UTF_8),new CorrelationData(uuid));
        return "success";
    }

    @GetMapping("/send/queue")
    public String sendQueue() {
        String uuid = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(ConfirmRabbitMQConfig.SCORPIOS_EXCHANGE_NAME, "TestQueue","message confirm callback ...".getBytes(StandardCharsets.UTF_8),new CorrelationData(uuid));
        return "success";
    }


}
