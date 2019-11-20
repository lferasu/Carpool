package edu.mum.ea.passenger.backend.entity;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.utils.UUIDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(value = "TripEntity")
public class TripEntity {

    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

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
