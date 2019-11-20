package edu.mum.ea.searchservice.model;


import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName="carpool",type="tripdata")
public class Trip implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String pickUpPlace;
    private String dropOffPlace;

    //@JsonFormat(pattern = "YYYY-MM-dd")
    private String tripStartingTime;
  
    //@JsonFormat(pattern = "YYYY-MM-dd")
    private String tripEndTime;


    private boolean isRoundTrip;
    private Integer numberOfAvilableSeats;
    private Double tripPrice;
    private String tripDescription;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private Integer numberDate;
    private Long driverId;

    private String adrStreet;
    private String adrCity;
    private String adrState;
    private String adrZip;


    public Trip() {
    }

    public Trip(Long id, String pickUpPlace, String dropOffPlace, String tripStartingTime, String tripEndTime, boolean isRoundTrip, Integer numberOfAvilableSeats, Double tripPrice, String tripDescription, String firstName, String lastName, String emailAddress, Integer numberDate, Long driverId, String adrStreet, String adrCity, String adrState, String adrZip) {
        this.id = id;
        this.pickUpPlace = pickUpPlace;
        this.dropOffPlace = dropOffPlace;
        this.tripStartingTime = tripStartingTime;
        this.tripEndTime = tripEndTime;
        this.isRoundTrip = isRoundTrip;
        this.numberOfAvilableSeats = numberOfAvilableSeats;
        this.tripPrice = tripPrice;
        this.tripDescription = tripDescription;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.numberDate = numberDate;
        this.driverId = driverId;
        this.adrStreet = adrStreet;
        this.adrCity = adrCity;
        this.adrState = adrState;
        this.adrZip = adrZip;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickUpPlace() {
        return this.pickUpPlace;
    }

    public void setPickUpPlace(String pickUpPlace) {
        this.pickUpPlace = pickUpPlace;
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

    public Integer getNumberOfAvilableSeats() {
        return this.numberOfAvilableSeats;
    }

    public void setNumberOfAvilableSeats(Integer numberOfAvilableSeats) {
        this.numberOfAvilableSeats = numberOfAvilableSeats;
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

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getNumberDate() {
        return this.numberDate;
    }

    public void setNumberDate(Integer numberDate) {
        this.numberDate = numberDate;
    }

    public Long getDriverId() {
        return this.driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getAdrStreet() {
        return this.adrStreet;
    }

    public void setAdrStreet(String adrStreet) {
        this.adrStreet = adrStreet;
    }

    public String getAdrCity() {
        return this.adrCity;
    }

    public void setAdrCity(String adrCity) {
        this.adrCity = adrCity;
    }

    public String getAdrState() {
        return this.adrState;
    }

    public void setAdrState(String adrState) {
        this.adrState = adrState;
    }

    public String getAdrZip() {
        return this.adrZip;
    }

    public void setAdrZip(String adrZip) {
        this.adrZip = adrZip;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", pickUpPlace='" + getPickUpPlace() + "'" +
            ", dropOffPlace='" + getDropOffPlace() + "'" +
            ", tripStartingTime='" + getTripStartingTime() + "'" +
            ", tripEndTime='" + getTripEndTime() + "'" +
            ", isRoundTrip='" + isIsRoundTrip() + "'" +
            ", numberOfAvilableSeats='" + getNumberOfAvilableSeats() + "'" +
            ", tripPrice='" + getTripPrice() + "'" +
            ", tripDescription='" + getTripDescription() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", numberDate='" + getNumberDate() + "'" +
            ", driverId='" + getDriverId() + "'" +
            ", adrStreet='" + getAdrStreet() + "'" +
            ", adrCity='" + getAdrCity() + "'" +
            ", adrState='" + getAdrState() + "'" +
            ", adrZip='" + getAdrZip() + "'" +
            "}";
    }



}
    
