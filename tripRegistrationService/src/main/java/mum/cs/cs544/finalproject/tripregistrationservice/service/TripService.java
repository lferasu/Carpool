package mum.cs.cs544.finalproject.tripregistrationservice.service;
import mum.cs.cs544.finalproject.tripregistrationservice.model.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface TripService {
    Trip  saveTrip(Trip trip);
    List<Trip> getAllTrips();
    Trip getTripById(UUID id);

}