package com.scorpios.rabbitmq.consumer;

import com.scorpios.rabbitmq.config.HeaderRabbitMQConfig;
import com.scorpios.rabbitmq.config.TopicsRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeaderConsumer {

    @RabbitListener(queues = HeaderRabbitMQConfig.SCORPIOS_QUEUE_NAME_ONE)
    public void consumeName(String msg) {
        log.info("consumeName消费者收到的消息为：{}",msg);
    }

    @RabbitListener(queues = HeaderRabbitMQConfig.SCORPIOS_QUEUE_NAME_TWO)
    public void consumeAge(String msg) {
        log.info("consumeAge消费者收到的消息为：{}",msg);
    }

}
