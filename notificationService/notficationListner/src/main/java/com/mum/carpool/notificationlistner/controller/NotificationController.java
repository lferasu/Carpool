package com.mum.carpool.notificationlistner.controller;

import com.mum.carpool.notificationlistner.Service.NotificationService;
import com.mum.carpool.notificationlistner.model.Notification;
import com.mum.carpool.notificationlistner.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/{username}")
    public ResponseEntity<List<Notification>> getNotificationForUser(@PathVariable String username) {

        try{
            List<Notification> notifications = notificationService.getNotificationForUser(username);
            return  new ResponseEntity<List<Notification>>(notifications, HttpStatus.OK);
        } catch (Exception e) {
            throw new DataAccessResourceFailureException(e.getMessage());
        }
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }




}



