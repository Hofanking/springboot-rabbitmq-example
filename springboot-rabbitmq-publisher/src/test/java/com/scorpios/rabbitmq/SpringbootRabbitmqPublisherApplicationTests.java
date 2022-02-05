package com.scorpios.rabbitmq;

import com.scorpios.rabbitmq.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitmqPublisherApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.SCORPIOS_QUEUE_NAME, "hello");
    }

}
