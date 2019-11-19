package mum.cs.cs544.finalproject.tripregistrationservice.model;

import com.datastax.driver.core.LocalDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchTripKafka implements Serializable {

    private long id;
    private String pickupPlace;
    private String dropOffPlace;
    private String tripStartingTime;
    private String tripEndTime;
    private boolean isRoundTrip;
    private Integer numberOfAvailableSeats;
    private int requestedReserveSeat;
    private Double tripPrice;
    private String tripDescription;
    private User driver;
    //private Reservation reservation;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public SearchTripKafka() {
    }

    public SearchTripKafka(Trip trip){
        this.id=trip.getId();
        this.pickupPlace=trip.getPickupPlace();
        this.dropOffPlace=trip.getDropOffPlace();
        this.tripStartingTime=trip.getTripStartingTime().format(formatter);
        this.tripEndTime=trip.getTripEndTime().format(formatter);
        this.isRoundTrip=trip.isRoundTrip();
        this.numberOfAvailableSeats=trip.getNumberOfAvailableSeats();
        this.requestedReserveSeat=trip.getRequestedReserveSeat();
        this.tripPrice=trip.getTripPrice();
        this.tripDescription=trip.getTripDescription();
        this.driver=trip.getDriver();
        //this.reservation=trip.getReservation();

    }

    @Override
    public String toString() {
        return "SearchTripKafka{" +
                "id=" + id +
                ", pickupPlace='" + pickupPlace + '\'' +
                ", dropOffPlace='" + dropOffPlace + '\'' +
                ", tripStartingTime='" + tripStartingTime + '\'' +
                ", tripEndTime='" + tripEndTime + '\'' +
                ", isRoundTrip=" + isRoundTrip +
                ", numberOfAvailableSeats=" + numberOfAvailableSeats +
                ", requestedReserveSeat=" + requestedReserveSeat +
                ", tripPrice=" + tripPrice +
                ", tripDescription='" + tripDescription + '\'' +
                ", driver=" + driver +
                '}';
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPickupPlace() {
        return pickupPlace;
    }

    public void setPickupPlace(String pickupPlace) {
        this.pickupPlace = pickupPlace;
    }

    public String getDropOffPlace() {
        return dropOffPlace;
    }

    public void setDropOffPlace(String dropOffPlace) {
        this.dropOffPlace = dropOffPlace;
    }

    public String getTripStartingTime() {
        return tripStartingTime;
    }

    public void setTripStartingTime(String tripStartingTime) {
        this.tripStartingTime = tripStartingTime;
    }

    public String getTripEndTime() {
        return tripEndTime;
    }

    public void setTripEndTime(String tripEndTime) {
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

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    public static void setFormatter(DateTimeFormatter formatter) {
        SearchTripKafka.formatter = formatter;
    }
}
