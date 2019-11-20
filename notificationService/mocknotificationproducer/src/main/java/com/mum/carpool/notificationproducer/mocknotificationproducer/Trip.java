package com.mum.carpool.notificationproducer.mocknotificationproducer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
