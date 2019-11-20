package com.mum.carpool.notificationlistner.repository;

import com.mum.carpool.notificationlistner.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, Integer> {
    List<Notification> findAllByUserName(String userName);
}

