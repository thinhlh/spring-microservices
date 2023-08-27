package com.thinhlh.clients.notification;

import lombok.Builder;

@Builder
public record NotificationRequest(
        String message,
        String receiver
) {

}
