package mum.cs.cs544.finalproject.tripregistrationservice.respository;

import mum.cs.cs544.finalproject.tripregistrationservice.model.Trip;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface TripRepository extends CassandraRepository<Trip, UUID> {

}