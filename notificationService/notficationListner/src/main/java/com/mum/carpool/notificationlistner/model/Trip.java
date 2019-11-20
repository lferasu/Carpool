package com.mum.carpool.notificationlistner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mum.carpool.notificationlistner.model.Reservation;
import com.mum.carpool.notificationlistner.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    private long id;
    private String pickupPlace;
    private String dropOffPlace;
    private LocalDateTime tripStartingTime;
    private LocalDateTime tripEndTime;
    private boolean isRoundTrip;
    private Integer numberOfAvailableSeats;
    private int requestedReserveSeat;
    private Double tripPrice;
    private String tripDescription;
    private User driver;
    private Reservation reservation;

}
