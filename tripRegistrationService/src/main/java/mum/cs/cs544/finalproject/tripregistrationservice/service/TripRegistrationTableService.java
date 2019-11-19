package mum.cs.cs544.finalproject.tripregistrationservice.service;

import mum.cs.cs544.finalproject.tripregistrationservice.model.Reservation;
import mum.cs.cs544.finalproject.tripregistrationservice.model.Trip;
import mum.cs.cs544.finalproject.tripregistrationservice.model.SearchTripKafka;
import mum.cs.cs544.finalproject.tripregistrationservice.model.TripRegistrationTable;
import java.util.List;


public interface TripRegistrationTableService {

    TripRegistrationTable saveTrip(TripRegistrationTable tripRegistrationTable);

    TripRegistrationTable registrationTableMapper(long driverId, Trip trip);

    Trip tripMapper(TripRegistrationTable tripRegistrationTable);

    List<TripRegistrationTable>getAllRegTrips();

    void publishTrip(Trip trip);
    void publishTripKafka(SearchTripKafka searchTripKafka);

     List<Reservation> listOfReservatoin();

     TripRegistrationTable getTripRegById(long id);
}