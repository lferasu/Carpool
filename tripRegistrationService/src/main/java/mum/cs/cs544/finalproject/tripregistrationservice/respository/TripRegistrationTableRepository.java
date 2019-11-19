package mum.cs.cs544.finalproject.tripregistrationservice.respository;

import mum.cs.cs544.finalproject.tripregistrationservice.model.TripRegistrationTable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface TripRegistrationTableRepository extends CassandraRepository<TripRegistrationTable, Long> {

}