package com.amigoscode.notification.clients.notification;

import com.amigoscode.notification.clients.fraud.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification")
public interface NotificationClient {

    @PostMapping(path = "/api/v1/notification")
    FraudCheckResponse sendNotificacion(
            @RequestBody NotificationRequest notificationRequest
    );
}
