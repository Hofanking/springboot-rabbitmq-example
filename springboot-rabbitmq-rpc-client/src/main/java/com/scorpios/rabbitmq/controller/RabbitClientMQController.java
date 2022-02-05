package com.scorpios.rabbitmq.controller;


import com.scorpios.rabbitmq.config.RPCClientRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class RabbitClientMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{message}")
    public String send(@PathVariable("message") String message) {

        // 创建消息对象
        Message newMessage = MessageBuilder.withBody(message.getBytes()).build();

        log.info("Client 发送的消息为：{}", newMessage);

        // 客户端给消息队列发送消息，并返回响应结果
        Message result = rabbitTemplate.sendAndReceive(RPCClientRabbitMQConfig.SCORPIOS_RPC_EXCHANGE_NAME, RPCClientRabbitMQConfig.SCORPIOS_RPC_MSG_QUEUE, newMessage);

        String response = "";
        if (result != null) {
            // 获取已发送的消息的 correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            log.info("发送消息的correlationId为：{}", correlationId);

            // 获取响应头信息
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();

            // 获取 server 返回的消息 correlationId
            String msgId = (String) headers.get("spring_returned_message_correlation");

            // 将已发送的消息的 correlationId与server返回的消息 correlationId进行对比，相同则取出响应结果
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
                log.info("client 收到的响应结果为：{}", response);
            }
        }
        return response;
    }

}
