package com.scorpios.rabbitmq.controller;


import com.scorpios.rabbitmq.config.HeaderRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RabbitMQController {

    @Autowired
    RabbitTemplate rabbitTemplate;

//    @GetMapping("/send/message")
//    public String test(){
//        log.info("接收到客户端消息");
//        rabbitTemplate.convertAndSend(DirectRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,null,"hello scorpios...");
//        return "success";
//    }


//    @GetMapping("/send/message")
//    public String test(){
//        log.info("接收到客户端消息");
//        // routingkey 参数为null
//        rabbitTemplate.convertAndSend(FanoutRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,null,"hello scorpios... FanoutExchange");
//        return "success";
//    }

//    @GetMapping("/send/message")
//    public String test(){
//        log.info("接收到客户端消息");
//        rabbitTemplate.convertAndSend(TopicsRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,"xiaomi.news","小米新闻,xiao.news");
//        rabbitTemplate.convertAndSend(TopicsRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,"huawei.news","华为新闻,huawei.news");
//        rabbitTemplate.convertAndSend(TopicsRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,"xiaomi.phone","小米手机,xiaomi.phone");
//        rabbitTemplate.convertAndSend(TopicsRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,"huawei.phone","华为手机,huawei.phone");
//        rabbitTemplate.convertAndSend(TopicsRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,"phone.news","手机新闻,phone.news");
//        return "success";
//    }

    @GetMapping("/send/message")
    public String test(){
        log.info("接收到客户端消息");
        Message name = MessageBuilder.withBody("header exchange, scorpios_queue_name_name".getBytes())
                                        .setHeader("name", "scorpios").build();
        Message age = MessageBuilder.withBody("header exchange, scorpios_queue_name_age".getBytes())
                                        .setHeader("age", "20").build();
        rabbitTemplate.convertAndSend(HeaderRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,null,name);
        rabbitTemplate.convertAndSend(HeaderRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,null,age);
        return "success";
    }

}
