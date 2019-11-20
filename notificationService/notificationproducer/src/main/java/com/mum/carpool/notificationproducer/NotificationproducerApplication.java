package com.mum.carpool.notificationproducer;

import com.mum.carpool.notificationproducer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class NotificationproducerApplication implements CommandLineRunner {

    @Autowired
    KafkaTemplate<String, Trip> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(NotificationproducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Address address =  new Address("street", "address", "zip");
        User driver = new User("firstName", "lastNme", "username", "emailAddress");
        User reserver = new User("r_firstName", "r_lastNme", "r_username", "r_emailAddress");
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservation = new Reservation(6, reserver);
        reservations.add(reservation);

        Trip trip = new Trip(
                "mum",
                "Walmart",
                 LocalDateTime.now(),
                 LocalDateTime.now(),
                false,
                3,
                2,
                5.0,
                "If you want to go to Walmart and grab something, join me!",
                driver,
                reservations);
        kafkaTemplate.send("NEWRESERVATION", trip);
    }


}
