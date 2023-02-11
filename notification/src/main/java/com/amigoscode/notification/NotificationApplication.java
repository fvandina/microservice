package com.amigoscode.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.amigoscode.notification",
                "com.amigoscode.amqp"
        }
)
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.amigoscode.clients"
)
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//     CommandLineRunner commandLineRunner (
//             RabbitMQMessageProducer producer,
//             NotificationConfig notificationConfig){
//        return args -> {
//            producer.publish(
//                    new Person("Ali", 18),
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey()
//            );
//        };
//     }
//
//     record Person (String name, int age){}
}
