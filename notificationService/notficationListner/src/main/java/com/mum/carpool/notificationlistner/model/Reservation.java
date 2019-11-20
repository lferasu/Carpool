package com.mum.carpool.notificationlistner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Reservation {
    private long id;
    private int noOfReservedSeats;
    private boolean canceled = false;
    private User reservedBy;
}
