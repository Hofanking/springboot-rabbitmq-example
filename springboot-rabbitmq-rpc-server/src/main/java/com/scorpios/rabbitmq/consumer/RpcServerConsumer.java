package com.scorpios.rabbitmq.consumer;

import com.scorpios.rabbitmq.config.RPCServerRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RpcServerConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    // 此消费者消费msgQueue队列中的消息
    @RabbitListener(queues = RPCServerRabbitMQConfig.SCORPIOS_RPC_MSG_QUEUE)
    public void process(Message msg) {
        log.info("server 收到msgQueue队列中的消息为 : {}",msg.toString());
        Message response = MessageBuilder.withBody(("我是服务端Server，收到的消息为:"+new String(msg.getBody())).getBytes()).build();
        // 把收到的消息的CorrelationId取出
        CorrelationData correlationData = new CorrelationData(msg.getMessageProperties().getCorrelationId());
        // 想replyQueue队列发送确认消息
        rabbitTemplate.sendAndReceive(RPCServerRabbitMQConfig.SCORPIOS_RPC_EXCHANGE_NAME, RPCServerRabbitMQConfig.SCORPIOS_RPC_REPLY_QUEUE, response, correlationData);
    }
}
