package com.scorpios.rabbitmq.consumer;

import com.scorpios.rabbitmq.config.DirectRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Slf4j
//@Component
public class DirectConsumer {

//    @RabbitListener(queues = DirectRabbitMQConfig.SCORPIOS_QUEUE_NAME)
//    public void consume(String msg) {
//        log.info("consume消费者收到的消息为：{}",msg);
//    }

}
