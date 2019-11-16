package mum.cs.cs544.finalproject.tripregistrationservice.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table
public class User {

    @PrimaryKey
    private UUID id;
    //User is to be accessed from the User registration service
}
