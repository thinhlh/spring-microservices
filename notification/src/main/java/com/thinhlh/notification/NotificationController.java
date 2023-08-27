package com.thinhlh.notification;

import com.thinhlh.clients.notification.NotificationRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notifications")
public record NotificationController(
        NotificationService notificationService
) {
    @PostMapping("")
    Boolean sendNotification(@Validated @RequestBody NotificationRequest notificationRequest) {
        return notificationService.sendNotification(notificationRequest);
    }
}
