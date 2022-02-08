package com.scorpios.rabbitmq.controller;


import com.scorpios.rabbitmq.service.SendMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RabbitMQController {


    @Autowired
    SendMessageService sendMessageService;

    @GetMapping("/send/message")
    public String send() {

       sendMessageService.sendMessage();
       return "success";
    }
}
