package edu.mum.ea.searchservice.model;

import java.time.LocalDateTime;

public class TripSender {

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



    public TripSender() {
    }

    

    public TripSender(long id, String pickupPlace, String dropOffPlace, LocalDateTime tripStartingTime, LocalDateTime tripEndTime, boolean isRoundTrip, Integer numberOfAvailableSeats, int requestedReserveSeat, Double tripPrice, String tripDescription, User driver, Reservation reservation) {
        this.id = id;
        this.pickupPlace = pickupPlace;
        this.dropOffPlace = dropOffPlace;
        this.tripStartingTime = tripStartingTime;
        this.tripEndTime = tripEndTime;
        this.isRoundTrip = isRoundTrip;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.requestedReserveSeat = requestedReserveSeat;
        this.tripPrice = tripPrice;
        this.tripDescription = tripDescription;
        this.driver = driver;
        this.reservation = reservation;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPickupPlace() {
        return this.pickupPlace;
    }

    public void setPickupPlace(String pickupPlace) {
        this.pickupPlace = pickupPlace;
    }

    public String getDropOffPlace() {
        return this.dropOffPlace;
    }

    public void setDropOffPlace(String dropOffPlace) {
        this.dropOffPlace = dropOffPlace;
    }

    public LocalDateTime getTripStartingTime() {
        return this.tripStartingTime;
    }

    public void setTripStartingTime(LocalDateTime tripStartingTime) {
        this.tripStartingTime = tripStartingTime;
    }

    public LocalDateTime getTripEndTime() {
        return this.tripEndTime;
    }

    public void setTripEndTime(LocalDateTime tripEndTime) {
        this.tripEndTime = tripEndTime;
    }

    public boolean isIsRoundTrip() {
        return this.isRoundTrip;
    }

    public boolean getIsRoundTrip() {
        return this.isRoundTrip;
    }

    public void setIsRoundTrip(boolean isRoundTrip) {
        this.isRoundTrip = isRoundTrip;
    }

    public Integer getNumberOfAvailableSeats() {
        return this.numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(Integer numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public int getRequestedReserveSeat() {
        return this.requestedReserveSeat;
    }

    public void setRequestedReserveSeat(int requestedReserveSeat) {
        this.requestedReserveSeat = requestedReserveSeat;
    }

    public Double getTripPrice() {
        return this.tripPrice;
    }

    public void setTripPrice(Double tripPrice) {
        this.tripPrice = tripPrice;
    }

    public String getTripDescription() {
        return this.tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public User getDriver() {
        return this.driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }


    
}