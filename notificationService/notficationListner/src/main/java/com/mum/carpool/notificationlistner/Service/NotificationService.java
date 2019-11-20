package com.mum.carpool.notificationlistner.Service;


import com.mum.carpool.notificationlistner.model.Notification;

import java.util.List;

public interface NotificationService {
    public void saveTripNotification(List<Notification> notifications);
    public void sendKafkaNotification(Notification notifications);
    public List<Notification> getNotificationForUser(String username);
}
