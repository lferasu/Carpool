package edu.mum.ea.passenger.frontend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class TripEntity {

    private String id;

    private String passengerId;

    //private PassengerEntity passengerEntity;

    private String departureAddressId;
    //private AddressEntity departureAddress;

    @Future
    private LocalDateTime departureDateTime;

    private String arrivalAddressId;
//    private AddressEntity arrivalAddress;

    //for searching window time
    private LocalDateTime arrivalDateTimeStart;
    @Future
    private LocalDateTime arrivalDateTimeEnd;

    private Integer reserveSeat = 1;

    private String baggageInfo = "";

    private Double offeredPrice = 0.0;

    @Value("${passenger.trip.kafkaTopic: passenger-trip}")
    private String kafkaTopic = "passenger-trip";

    private Long timeStamp;

//    public TripEntity toTripEntity(TripEntity t) {
//        TripEntity tripEntity =  new TripEntity();
//        tripEntity.setArrivalAddressId(t.getArrivalAddressId());
//        tripEntity.setArrivalDateTimeEnd(t.arrivalDateTimeEnd);
//        tripEntity.
//
//    }


}
