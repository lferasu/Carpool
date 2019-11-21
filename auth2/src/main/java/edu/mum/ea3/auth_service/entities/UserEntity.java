package edu.mum.ea2.auth_service.entities;

//import edu.mum.shared.models.User;
import lombok.*;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
<<<<<<< HEAD:auth2/src/main/java/edu/mum/ea3/auth_service/entities/UserEntity.java
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;
=======
import org.springframework.data.cassandra.core.mapping.Table;
>>>>>>> 19678fa99308909681f623056ba719e258900b07:auth/src/main/java/edu/mum/ea2/auth_service/entities/UserEntity.java

//import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
<<<<<<< HEAD:auth2/src/main/java/edu/mum/ea3/auth_service/entities/UserEntity.java
@Table("users")
=======
@Table("user")
>>>>>>> 19678fa99308909681f623056ba719e258900b07:auth/src/main/java/edu/mum/ea2/auth_service/entities/UserEntity.java
public class UserEntity {

//    private final long idInitial = 0;

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
    private UUID id;
    private String firstName;
    private String lastName;
    private String password;
   // @PrimaryKeyColumn(name = "email", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
    private String email;

    // address
    private String adrStreet;
    private String adrCity;
    private String adrState;
    private String adrZip;


    public UserEntity toUserModel(){
        UserEntity user = new UserEntity();
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
