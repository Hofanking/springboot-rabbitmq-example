package com.scorpios.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.scorpios.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ScorpiosConsumer {

//    @RabbitListener(queues = RabbitMQConfig.SCORPIOS_QUEUE_NAME)
//    public void consumeOne(String msg) {
//        log.info("consumeOne消费者收到的消息为：{}",msg);
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.SCORPIOS_QUEUE_NAME,concurrency = "5")
//    public void consumeTwo(String msg) {
//        log.info("consumeTwo消费者收到的消息为：{}，消费线程为：{}", msg, Thread.currentThread().getName());
//    }

    @RabbitListener(queues = RabbitMQConfig.SCORPIOS_QUEUE_NAME)
    public void consumeOne(Message message, Channel channel) throws IOException {
        // 确认消费消息
        channel.basicAck(((Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG)),true);
        log.info("consumeOne消费者收到的消息为：{}",message.getPayload());
    }

    @RabbitListener(queues = RabbitMQConfig.SCORPIOS_QUEUE_NAME,concurrency = "5")
    public void consumeTwo(Message message, Channel channel) throws IOException {
        // 拒绝消费消息
        channel.basicReject(((Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG)), true);
        log.info("consumeTwo消费者收到的消息为：{}，消费线程为：{}", message.getPayload(), Thread.currentThread().getName());
    }
}
