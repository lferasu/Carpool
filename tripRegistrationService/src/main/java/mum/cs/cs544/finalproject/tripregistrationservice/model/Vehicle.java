package mum.cs.cs544.finalproject.tripregistrationservice.model;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;
@Data
@Table
public class Vehicle {
    @PrimaryKey
    private UUID id;
    private String vehicleMake;
    private String vehicleModel;
    private String vehiclePlateNumber;
}

