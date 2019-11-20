package edu.mum.ea.searchservice.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ea.searchservice.model.Trip;

@Repository
public interface TripRepository extends ElasticsearchRepository<Trip, Long> {

	List<Trip> findByPickUpPlace(String pickUpPlace);
    
}