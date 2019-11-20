package com.mum.carpool.notificationlistner.Utils;

//import com.mum.carpool.notificationlistner.config.EmailConfiguration;
import com.mum.carpool.notificationlistner.config.EmailConfiguration;
import com.mum.carpool.notificationlistner.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmailUtil {

    private EmailConfiguration emailConfiguration;

    public EmailUtil(EmailConfiguration emailConfiguration) {
        this.emailConfiguration = emailConfiguration;
    }

    @Value("${appEmailAddress}")
    private String adminEmail;

    @Value("${emailSubject}")
    private String mailSubject;



    public EmailUtil () {

    }

    public  void sendEmail(Notification notification) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfiguration.getHost());
        mailSender.setPort(emailConfiguration.getPort());
        mailSender.setUsername(emailConfiguration.getUsername());
        mailSender.setPassword(emailConfiguration.getPassword());

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
