package edu.mum.ea.searchservice.model;

public class TripData {

    private Long id;

    private String pickupPlace;
    private String dropOffPlace;
    private String tripStartingTime;
    private String tripEndTime;
    private boolean isRoundTrip;
    private Integer numberOfAvailableSeats;
    private Double tripPrice;
    private String tripDescription;
    private User driver;



    public TripData() {
    }


    public TripData(Long id, String pickupPlace, String dropOffPlace, String tripStartingTime, String tripEndTime, boolean isRoundTrip, Integer numberOfAvailableSeats, Double tripPrice, String tripDescription, User driver) {
        this.id = id;
        this.pickupPlace = pickupPlace;
        this.dropOffPlace = dropOffPlace;
        this.tripStartingTime = tripStartingTime;
        this.tripEndTime = tripEndTime;
        this.isRoundTrip = isRoundTrip;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.tripPrice = tripPrice;
        this.tripDescription = tripDescription;
        this.driver = driver;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public String getTripStartingTime() {
        return this.tripStartingTime;
    }

    public void setTripStartingTime(String tripStartingTime) {
        this.tripStartingTime = tripStartingTime;
    }

    public String getTripEndTime() {
        return this.tripEndTime;
    }

    public void setTripEndTime(String tripEndTime) {
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



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", pickupPlace='" + getPickupPlace() + "'" +
            ", dropOffPlace='" + getDropOffPlace() + "'" +
            ", tripStartingTime='" + getTripStartingTime() + "'" +
            ", tripEndTime='" + getTripEndTime() + "'" +
            ", isRoundTrip='" + isIsRoundTrip() + "'" +
            ", numberOfAvailableSeats='" + getNumberOfAvailableSeats() + "'" +
            ", tripPrice='" + getTripPrice() + "'" +
            ", tripDescription='" + getTripDescription() + "'" +
            ", driver='" + getDriver() + "'" +
            "}";
    }
    
}