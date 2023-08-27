package com.thinhlh.notification;

import com.thinhlh.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record NotificationService(
        NotificationRepository notificationRepository
) {
    public Boolean sendNotification(NotificationRequest notificationRequest) {

        notificationRepository.save(
                Notification
                        .builder()
                        .receiver(notificationRequest.receiver())
                        .message(notificationRequest.message())
                        .build()
        );

        log.info("Notification sent {}", notificationRequest);
        return true;
    }
}
