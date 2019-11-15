package mum.cs.cs544.finalproject.tripregistrationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    private Address PickupPlace;
    private Address DropOffPlace;
    private LocalDateTime tripStartingTime;
    private LocalDateTime tripEndTime;
    private  Vehicle vehicle;
    private User user;
    private boolean isRoundTrip;
    private Integer numberOfAvailableSeats;
    private Double tripPrice;
    private String tripDescription;
}
