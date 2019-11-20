package mum.cs.cs544.finalproject.tripregistrationservice.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
@Data
public class Reservation {

    private long id;
    private int noOfReservedSeats;
    private boolean canceled = false;
    @JsonIgnore
    private User reservedBy;

    @JsonIgnore
    private Trip trip;
}
