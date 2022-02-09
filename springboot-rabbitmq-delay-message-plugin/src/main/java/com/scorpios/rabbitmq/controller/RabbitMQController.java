package com.scorpios.rabbitmq.controller;


import com.scorpios.rabbitmq.config.PluginRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RestController
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/message")
    public String send() {


        log.info("发送消息....");
        // 创建消息对象
        Message msg = MessageBuilder.withBody(("delay message plugin..." + new Date()).getBytes(StandardCharsets.UTF_8)).setHeader("x-delay", 10000).build();
        rabbitTemplate.convertAndSend(PluginRabbitMQConfig.SCORPIOS_EXCHANGE_NAME, PluginRabbitMQConfig.SCORPIOS_MSG_QUEUE, msg);
        return "success";
    }
}
