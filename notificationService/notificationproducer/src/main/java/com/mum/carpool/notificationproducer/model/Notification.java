package com.mum.carpool.notificationproducer.model;

//import org.springframework.data.mongodb.core.mapping.Document;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Document
public class Notification {
    private String userName;
    private String email;
    private String Message;

    public Notification(String userName, String email, String message) {
        this.userName = userName;
        this.email = email;
        Message = message;
    }

    public Notification() {
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

}
