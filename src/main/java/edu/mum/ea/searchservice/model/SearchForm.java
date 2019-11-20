package edu.mum.ea.searchservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SearchForm {

    private String from; 
    private String to;
    private LocalDateTime date;
    private Integer noOfSeats;
    private Double priceRangeFrom;
    private Double priceRangeUntil;


    public SearchForm() {
    }


    public SearchForm(String from, String to, LocalDateTime date, Integer noOfSeats, Double priceRangeFrom, Double priceRangeUntil) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.noOfSeats = noOfSeats;
        this.priceRangeFrom = priceRangeFrom;
        this.priceRangeUntil = priceRangeUntil;
    }


    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getNoOfSeats() {
        return this.noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Double getPriceRangeFrom() {
        return this.priceRangeFrom;
    }

    public void setPriceRangeFrom(Double priceRangeFrom) {
        this.priceRangeFrom = priceRangeFrom;
    }

    public Double getPriceRangeUntil() {
        return this.priceRangeUntil;
    }

    public void setPriceRangeUntil(Double priceRangeUntil) {
        this.priceRangeUntil = priceRangeUntil;
    }

    
}