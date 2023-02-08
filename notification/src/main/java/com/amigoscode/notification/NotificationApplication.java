package com.amigoscode.notification;

import com.amigoscode.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.amigoscode.notification",
                "com.amigoscode.amqp"
        }
)
@EnableDiscoveryClient
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
     CommandLineRunner commandLineRunner (
             RabbitMQMessageProducer producer,
             NotificationConfig notificationConfig){
        return args -> {
            producer.publish(
                    "foo",
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey()
            );
        };
     }
}
