package com.mum.carpool.notificationlistner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Document
public class Notification {
    @Id
    private String id;
    private String userName;
    private String email;
    private String Message;
    private String notificationType;
    private boolean isChecked;

    public Notification(String userName, String email, String message, String notificationType ) {
        this.userName = userName;
        this.email = email;
        Message = message;
        this.notificationType = notificationType;
        this.isChecked = false;
    }

    public Notification() {
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
