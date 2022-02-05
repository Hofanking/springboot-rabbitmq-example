package com.scorpios.rabbitmq.config;

//@Configuration
public class FanoutRabbitMQConfig {

    // 交换机的名称
    public static final String SCORPIOS_EXCHANGE_NAME = "scorpios_exchange_name";

    // 队列的名称
    public static final String SCORPIOS_QUEUE_NAME_ONE = "scorpios_queue_name_one";
    public static final String SCORPIOS_QUEUE_NAME_TWO = "scorpios_queue_name_two";
//
//    /**
//     * 创建一个FanoutExchange交换机
//     * 第一个参数：交换机名字
//     * 第二个参数：重启后是否依然有效
//     * 第三个参数：长期未用时是否删除
//     * @return
//     */
//    @Bean
//    FanoutExchange fanoutExchange(){
//        return new FanoutExchange(FanoutRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,true,false);
//    }
//
//    @Bean
//    Queue queueOne() {
//        return new Queue(FanoutRabbitMQConfig.SCORPIOS_QUEUE_NAME_ONE,true,false,false);
//    }
//
//    @Bean
//    Queue queueTwo() {
//        return new Queue(FanoutRabbitMQConfig.SCORPIOS_QUEUE_NAME_TWO,true,false,false);
//    }
//
//    // 将队列与FanoutExchange绑定
//    @Bean
//    Binding bindingOne() {
//        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
//    }
//
//    @Bean
//    Binding bindingTwo() {
//        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
//    }

}
