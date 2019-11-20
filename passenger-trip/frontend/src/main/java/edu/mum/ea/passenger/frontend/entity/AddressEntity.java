package edu.mum.ea.passenger.frontend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data@ToString@NoArgsConstructor@AllArgsConstructor
public class AddressEntity {

    private String id;

    private String street;
    private String city;
    private String state;
    private String zip;

}
