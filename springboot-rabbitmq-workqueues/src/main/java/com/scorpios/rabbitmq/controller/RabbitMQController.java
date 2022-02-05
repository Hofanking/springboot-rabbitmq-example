package com.scorpios.rabbitmq.controller;


import com.scorpios.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RabbitMQController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send/message")
    public String test(){
        log.info("接收到客户端消息，向RabbitMQ发送消息...");
        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.SCORPIOS_QUEUE_NAME, "hello scorpios...." + i);
        }
        log.info("-----------------------------------");
        return "success";
    }


}
