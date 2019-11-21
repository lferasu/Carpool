package com.mum.carpool.notificationlistner.Listner;

import com.mum.carpool.notificationlistner.Utils.NotificationUtil;
import com.mum.carpool.notificationlistner.Service.NotificationService;
//import com.mum.carpool.notificationlistner.config.EmailConfiguration;
import com.mum.carpool.notificationlistner.config.EmailConfiguration;
import com.mum.carpool.notificationlistner.model.Notification;
import com.mum.carpool.notificationlistner.model.Trip;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationListner {

    @Autowired
    NotificationService notificationService;
    @Value("${appEmailAddress}")
    private String adminEmail;

    @Value("${emailSubject}")
    private String mailSubject;

    private EmailConfiguration emailConfiguration;

    public NotificationListner(EmailConfiguration emailConfiguration) {
        this.emailConfiguration = emailConfiguration;
    }

   // private EmailConfiguration emailConfiguration;

        @KafkaListener(topics = "${kafka.topic.newreservation}", groupId = "group_id")
        public void consumeTrip (@Payload Trip trip,
                                 @Headers MessageHeaders headers)  {

            //Extract notification
            List<Notification> notifications = NotificationUtil.extractNotification(trip);
            // save notification to database
            if(notifications!=null) {
                notificationService.saveTripNotification(notifications);
                //send email
                List<Notification> filteredNotificationList = notifications.stream().filter(not->not.getEmail()!=null).collect(Collectors.toList());
                filteredNotificationList.forEach(this::sendEmail);
                //push kafka notification
                notifications.forEach(notificationService::sendKafkaNotification);
            }

        }

        private void sendEmail(Notification notification) {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.mailtrap.io");
            mailSender.setPort(25);
            mailSender.setUsername("7ebd686d62d48b");
            mailSender.setPassword("258180aa6560e6");
            //Create an email instance
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(adminEmail);
            mailMessage.setTo(notification.getEmail());
            mailMessage.setSubject(mailSubject + " : " + notification.getNotificationType());
            mailMessage.setText(notification.getMessage());
            // Send email
            mailSender.send(mailMessage);
        }
}


