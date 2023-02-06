package com.amigoscode.notification.clients.notification;

public record NotificationRequest(
        Integer id,
        String email,
        String message
) {
}
