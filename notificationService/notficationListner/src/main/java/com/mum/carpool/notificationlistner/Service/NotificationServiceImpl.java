package com.mum.carpool.notificationlistner.Service;

import com.mum.carpool.notificationlistner.Utils.EmailUtil;
import com.mum.carpool.notificationlistner.Utils.NotificationUtil;
import com.mum.carpool.notificationlistner.model.Notification;
import com.mum.carpool.notificationlistner.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    KafkaTemplate<String, Notification> kafkaTemplate;

    @Value("${kafka.topic.notification}")
    private String kafkaTopic;

    @Override
    public void saveTripNotification(List<Notification> notifications) {
        //Save all notifications
        notificationRepository.saveAll(notifications);
    }

    @Override
    public void sendKafkaNotification(Notification notification) {
        //push kafka notification
        kafkaTemplate.send(kafkaTopic,notification);
    }

    @Override
    public List<Notification> getNotificationForUser(String username) {
        return notificationRepository.findAllByUserName(username);
    }

}
