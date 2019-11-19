package mum.cs.cs544.finalproject.tripregistrationservice.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
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
    //private List<Reservation> reservations= new ArrayList<>(numberOfAvailableSeats);

}
