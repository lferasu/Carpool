package mum.cs.cs544.finalproject.tripregistrationservice.service;

import mum.cs.cs544.finalproject.tripregistrationservice.model.Trip;
import mum.cs.cs544.finalproject.tripregistrationservice.respository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TripServiceImpl implements  TripService{

    @Autowired
    private TripRepository tripRepository;

    @Override
    public Trip  saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }



    @Override
    public List<Trip> getAllTrips() {
        List<Trip> trips= new ArrayList<>();
        tripRepository.findAll().forEach(trips::add);
        return trips;
    }



    @Override
    public Trip getTripById(UUID id) {
        return  tripRepository.findById(id).get();
    }


}
