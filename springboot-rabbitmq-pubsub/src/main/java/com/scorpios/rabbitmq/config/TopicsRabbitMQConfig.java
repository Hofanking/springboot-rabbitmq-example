package com.scorpios.rabbitmq.config;

//@Configuration
public class TopicsRabbitMQConfig {

    // 交换机的名称
    public static final String SCORPIOS_EXCHANGE_NAME = "scorpios_exchange_name";

    // 队列的名称
    public static final String SCORPIOS_QUEUE_NAME_ONE = "scorpios_queue_name_xiaomi";
    public static final String SCORPIOS_QUEUE_NAME_TWO = "scorpios_queue_name_huawei";
    public static final String SCORPIOS_QUEUE_NAME_THREE = "scorpios_queue_name_phone";
//
//    /**
//     * 创建一个TopicExchange交换机
//     * 第一个参数：交换机名字
//     * 第二个参数：重启后是否依然有效
//     * 第三个参数：长期未用时是否删除
//     * @return
//     */
//    @Bean
//    TopicExchange topicExchange(){
//        return new TopicExchange(TopicsRabbitMQConfig.SCORPIOS_EXCHANGE_NAME,true,false);
//    }
//
//    @Bean
//    Queue xiaomi() {
//        return new Queue(TopicsRabbitMQConfig.SCORPIOS_QUEUE_NAME_ONE,true,false,false);
//    }
//
//    @Bean
//    Queue huawei() {
//        return new Queue(TopicsRabbitMQConfig.SCORPIOS_QUEUE_NAME_TWO,true,false,false);
//    }
//
//    @Bean
//    Queue phone() {
//        return new Queue(TopicsRabbitMQConfig.SCORPIOS_QUEUE_NAME_THREE,true,false,false);
//    }
//
//    // 将队列与TopicExchange绑定
//    @Bean
//    Binding bindingXiaomi() {
//        return BindingBuilder.bind(xiaomi()).to(topicExchange()).with("xiaomi.#");
//    }
//
//    @Bean
//    Binding bindingHuawei() {
//        return BindingBuilder.bind(huawei()).to(topicExchange()).with("huawei.#");
//    }
//
//    @Bean
//    Binding bindingPhone() {
//        return BindingBuilder.bind(phone()).to(topicExchange()).with("#.phone.#");
//    }

}
