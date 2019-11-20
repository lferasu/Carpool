package com.mum.carpool.notificationlistner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String emailAddress;

}
