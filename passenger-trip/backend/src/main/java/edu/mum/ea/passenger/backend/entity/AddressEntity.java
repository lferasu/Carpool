package edu.mum.ea.passenger.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

@Data@ToString@NoArgsConstructor@AllArgsConstructor
public class AddressEntity {

    @PrimaryKey
    private String id;

    private String street;
    private String city;
    private String state;
    private String zip;

}
