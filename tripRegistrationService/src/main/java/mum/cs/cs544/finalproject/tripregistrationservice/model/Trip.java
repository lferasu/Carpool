package mum.cs.cs544.finalproject.tripregistrationservice.model;

import com.datastax.driver.core.DataType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("trips")
public class Trip {
    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;
    private String  PickupPlace;
    private String  DropOffPlace;
    private LocalDateTime tripStartingTime;
    private LocalDateTime tripEndTime;
    private boolean isRoundTrip;
    private Integer numberOfAvailableSeats;
    private Double tripPrice;
    private String tripDescription;
    private UUID userId;
    private UUID vehicleId;

}
