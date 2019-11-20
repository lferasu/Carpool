package edu.mum.ea.passenger.backend.repository;

import edu.mum.ea.passenger.backend.entity.TripEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PassengerTripRepository extends CassandraRepository<TripEntity, UUID> {

    List<TripEntity> findTripEntitiesByArrivalDateTimeEndIsLessThanAndArrivalDateTimeStartGreaterThan(LocalDateTime start, LocalDateTime end);
}
