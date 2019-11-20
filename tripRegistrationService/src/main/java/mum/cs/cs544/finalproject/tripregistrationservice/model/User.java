package mum.cs.cs544.finalproject.tripregistrationservice.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String emailAddress;

    //private String phoneNumber;
    //This is just before user is to be modified

    // address
    private String adrStreet;
    private String adrCity;
    private String adrState;
    private String adrZip;

}
