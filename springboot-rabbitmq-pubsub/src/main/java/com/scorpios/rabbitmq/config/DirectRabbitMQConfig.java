package com.scorpios.rabbitmq.config;

//@Configuration
public class DirectRabbitMQConfig {


    // 交换机的名称
    public static final String SCORPIOS_EXCHANGE_NAME = "scorpios_exchange_name";
//
//
    // 队列的名称
    public static final String SCORPIOS_QUEUE_NAME = "scorpios_queue_name";
//
//
//    /**
//     * 创建一个DirectExchange交换机
//     * 第一个参数：交换机名字
//     * 第二个参数：重启后是否依然有效
//     * 第三个参数：长期未用时是否删除
//     * @return
//     */
//    @Bean
//    DirectExchange directExchange(){
//        return new DirectExchange(DirectRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,true,false);
//    }
//
//    @Bean
//    Queue queue() {
//        return new Queue(DirectRabbitMQConfig.SCORPIOS_QUEUE_NAME,true,false,false);
//    }
//
//
//    // 将队列与DirectExchange绑定
//    @Bean
//    Binding binding() {
//        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
//    }

}
