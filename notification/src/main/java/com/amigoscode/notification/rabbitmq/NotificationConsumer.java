package com.amigoscode.notification.rabbitmq;

import com.amigoscode.clients.notification.NotificationRequest;
import com.amigoscode.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lguzman
 * @version 1.0
 * @since 08/02/2023
 */
@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;
    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest notificationRequest){
      log.info("Consumed {} from queue ", notificationRequest);
      notificationService.send(notificationRequest);
    }
}
