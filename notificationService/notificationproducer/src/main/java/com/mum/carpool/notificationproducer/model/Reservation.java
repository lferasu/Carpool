package com.mum.carpool.notificationproducer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Reservation {
    private int noOfReservedSeats;
    private User reserveBy;

    public Reservation(int noOfReservedSeats, User reserveBy) {
        this.noOfReservedSeats = noOfReservedSeats;
        this.reserveBy = reserveBy;
    }

    public Reservation() {
    }

    public int getNoOfReservedSeats() {
        return noOfReservedSeats;
    }

    public void setNoOfReservedSeats(int noOfReservedSeats) {
        this.noOfReservedSeats = noOfReservedSeats;
    }

    public User getReserveBy() {
        return reserveBy;
    }

    public void setReserveBy(User reserveBy) {
        this.reserveBy = reserveBy;
    }
}
