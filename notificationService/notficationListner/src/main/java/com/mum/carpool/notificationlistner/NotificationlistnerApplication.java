package com.mum.carpool.notificationlistner;

//import com.mum.carpool.notificationlistner.config.EmailConfiguration;
import com.mum.carpool.notificationlistner.config.EmailConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotificationlistnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationlistnerApplication.class, args);
    }


}
