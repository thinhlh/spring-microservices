package com.thinhlh.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification"
)
public interface NotificationClient {

    @PostMapping("api/v1/notifications")
    Boolean sendNotification(@Validated @RequestBody NotificationRequest notificationRequest);
}
