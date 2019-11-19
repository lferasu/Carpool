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
}
