package edu.mum.ea2.auth_service.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
	private int id;

	private String firstName;
	private String lastName;
	private String email;

	// shipping address
	private String adrStreet;
	private String adrCity;
	private String adrState;
	private String adrZip;

}
