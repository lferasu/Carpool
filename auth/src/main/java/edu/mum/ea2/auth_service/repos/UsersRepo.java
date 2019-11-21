package edu.mum.ea2.auth_service.repos;

import edu.mum.ea2.auth_service.entities.UserEntity;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends CrudRepository<UserEntity, Integer> {
    @AllowFiltering
    UserEntity findByEmail(String email);

    @AllowFiltering
	UserEntity findById(int id);

//    List<UserEntity> findAll();

    //void deleteByEmail(String email);


}
