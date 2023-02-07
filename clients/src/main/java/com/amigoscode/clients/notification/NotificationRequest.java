package com.amigoscode.clients.notification;

public record NotificationRequest(
        Integer customerId,
        String sender,
        String email,
        String message
) {
}
