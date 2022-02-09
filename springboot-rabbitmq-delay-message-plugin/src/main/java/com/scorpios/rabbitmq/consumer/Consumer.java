package com.scorpios.rabbitmq.consumer;

import com.scorpios.rabbitmq.config.PluginRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = PluginRabbitMQConfig.SCORPIOS_MSG_QUEUE)
    public void consume(String msg) {
        log.info("队列收到的消息为：{}", msg);
    }
}
