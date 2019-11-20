package edu.mum.ea.searchservice.model;

public class User {
    private Long id;
    private String firstName;
    private String lastName;

    //@PrimaryKey
    private String email;

    // address
    private String adrStreet;
    private String adrCity;
    private String adrState;
    private String adrZip;


    public User() {
    }

    public User(Long id, String firstName, String lastName, String email, String adrStreet, String adrCity, String adrState, String adrZip) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.adrStreet = adrStreet;
        this.adrCity = adrCity;
        this.adrState = adrState;
        this.adrZip = adrZip;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdrStreet() {
        return this.adrStreet;
    }

    public void setAdrStreet(String adrStreet) {
        this.adrStreet = adrStreet;
    }

    public String getAdrCity() {
        return this.adrCity;
    }

    public void setAdrCity(String adrCity) {
        this.adrCity = adrCity;
    }

    public String getAdrState() {
        return this.adrState;
    }

    public void setAdrState(String adrState) {
        this.adrState = adrState;
    }

    public String getAdrZip() {
        return this.adrZip;
    }

    public void setAdrZip(String adrZip) {
        this.adrZip = adrZip;
    }



    
}