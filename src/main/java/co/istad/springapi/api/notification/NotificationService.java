package co.istad.springapi.api.notification;

import co.istad.springapi.api.notification.web.CreateNotificationDto;
import co.istad.springapi.api.notification.web.NotificationDto;

public interface NotificationService {
    boolean pushNotification(CreateNotificationDto notificationDto);
}
