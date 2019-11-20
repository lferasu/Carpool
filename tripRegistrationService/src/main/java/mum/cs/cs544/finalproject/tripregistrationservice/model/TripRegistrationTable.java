package mum.cs.cs544.finalproject.tripregistrationservice.model;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("registrationtable")
public class TripRegistrationTable {

    @PrimaryKeyColumn(name="trip_id", type= PrimaryKeyType.PARTITIONED, ordering = Ordering.ASCENDING)
    private long tripId;
    private long driverId;
    private long reservationId;


    //Trip detail
    private String pickupPlace;
    private String dropOffPlace;
    private LocalDateTime tripStartingTime;
    private LocalDateTime tripEndTime;
    private boolean isRoundTrip;
    private Integer numberOfAvailableSeats;
    private Double tripPrice;
    private String tripDescription;

    //Driver detail
    private String firstName;
    private String lastName;
    private String username;
    private String emailAddress;

   /* // address
    private String adrStreet;
    private String adrCity;
    private String adrState;
    private String adrZip;*/

}