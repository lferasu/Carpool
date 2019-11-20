package edu.mum.reservetrip.model;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("reservationtable")
public class ReservationTable {

    @PrimaryKeyColumn(name = "trip_id", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
    private long tripId;

    private int noOfReservedSeats;
    private boolean canceled = false;
    private long userId;
    private String userFirstName;
    private String userLastName;
    private String userUsername;
    private String userEmailAddress;
    private long driverId;
    private String driverFirstName;
    private String driverLastName;
    private String driverUsername;
    private String driverEmailAddress;

    private String pickupPlace;
    private String dropOffPlace;
    private LocalDateTime tripStartingTime;
    private LocalDateTime tripEndTime;
    private boolean isRoundTrip;
    private Integer numberOfAvailableSeats;
    private int requestedReserveSeat;
    private Double tripPrice;
    private String tripDescription;
}
