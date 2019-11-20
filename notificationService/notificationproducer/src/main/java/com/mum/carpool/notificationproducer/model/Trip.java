package com.mum.carpool.notificationproducer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Trip {
    private String PickupPlace;
    private String DropOffPlace;
    private LocalDateTime tripStartingTime;
    private LocalDateTime tripEndTime;
    private boolean isRoundTrip;
    private Integer numberOfAvailableSeats;
    private int requestedReserveSeat;
    private Double tripPrice;
    private String tripDescription;
    private User driver;
    private List<Reservation> reservations;

    public Trip() {
    }

    public Trip(String pickupPlace, String dropOffPlace, LocalDateTime tripStartingTime, LocalDateTime tripEndTime, boolean isRoundTrip, Integer numberOfAvailableSeats, int requestedReserveSeat, Double tripPrice, String tripDescription, User driver, List<Reservation> reservations) {
        PickupPlace = pickupPlace;
        DropOffPlace = dropOffPlace;
        this.tripStartingTime = tripStartingTime;
        this.tripEndTime = tripEndTime;
        this.isRoundTrip = isRoundTrip;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.requestedReserveSeat = requestedReserveSeat;
        this.tripPrice = tripPrice;
        this.tripDescription = tripDescription;
        this.driver = driver;
        this.reservations = reservations;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getPickupPlace() {
        return PickupPlace;
    }

    public void setPickupPlace(String pickupPlace) {
        PickupPlace = pickupPlace;
    }

    public String getDropOffPlace() {
        return DropOffPlace;
    }

    public void setDropOffPlace(String dropOffPlace) {
        DropOffPlace = dropOffPlace;
    }

    public LocalDateTime getTripStartingTime() {
        return tripStartingTime;
    }

    public void setTripStartingTime(LocalDateTime tripStartingTime) {
        this.tripStartingTime = tripStartingTime;
    }

    public LocalDateTime getTripEndTime() {
        return tripEndTime;
    }

    public void setTripEndTime(LocalDateTime tripEndTime) {
        this.tripEndTime = tripEndTime;
    }

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public void setRoundTrip(boolean roundTrip) {
        isRoundTrip = roundTrip;
    }

    public Integer getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(Integer numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public int getRequestedReserveSeat() {
        return requestedReserveSeat;
    }

    public void setRequestedReserveSeat(int requestedReserveSeat) {
        this.requestedReserveSeat = requestedReserveSeat;
    }

    public Double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(Double tripPrice) {
        this.tripPrice = tripPrice;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}

