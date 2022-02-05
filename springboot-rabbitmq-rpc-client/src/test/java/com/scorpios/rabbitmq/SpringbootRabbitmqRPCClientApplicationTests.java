package com.scorpios.rabbitmq;

import com.scorpios.rabbitmq.config.RPCClientRabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitmqRPCClientApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend(RPCClientRabbitMQConfig.SCORPIOS_RPC_MSG_QUEUE, "hello");
    }

}
