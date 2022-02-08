package com.scorpios.rabbitmq.consumer;

import com.scorpios.rabbitmq.config.DLXRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = DLXRabbitMQConfig.SCORPIOS_DLX_QUEUE)
    public void dlxConsume(String msg) {
        log.info("死信队列收到的消息为：{}", msg);
    }
}
