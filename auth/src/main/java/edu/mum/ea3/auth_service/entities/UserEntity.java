package edu.mum.ea3.auth_service.entities;

import edu.mum.shared.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

//import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
//@Entity
@ToString
//@Table(name = "Users")
public class UserEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @PrimaryKey
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    //@PrimaryKey
    private String email;

    // address
    private String adrStreet;
    private String adrCity;
    private String adrState;
    private String adrZip;


    public User toUserModel(){
        User user = new User();
        user.setId(this.id);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);

        user.setAdrStreet(this.adrStreet);
        user.setAdrCity(this.adrCity);
        user.setAdrState(this.adrState);
        user.setAdrZip(this.adrZip);

        user.setAdrZip(this.adrZip);

        return user;
    }


}
