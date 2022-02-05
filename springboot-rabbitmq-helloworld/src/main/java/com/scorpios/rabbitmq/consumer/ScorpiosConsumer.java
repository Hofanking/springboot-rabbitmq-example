package com.scorpios.rabbitmq.consumer;

import com.scorpios.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScorpiosConsumer {

    @RabbitListener(queues = RabbitMQConfig.SCORPIOS_QUEUE_NAME)
    public void consume(String msg) {
        log.info("消费者收到的消息为：{}",msg);
    }
}
