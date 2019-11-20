package com.mum.carpool.notificationlistner.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Data
@Component
public class EmailConfiguration {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private Integer port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

}
