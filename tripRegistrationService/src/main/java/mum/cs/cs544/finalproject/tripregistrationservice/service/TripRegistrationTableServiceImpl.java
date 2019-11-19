package mum.cs.cs544.finalproject.tripregistrationservice.service;
import mum.cs.cs544.finalproject.tripregistrationservice.model.*;
import mum.cs.cs544.finalproject.tripregistrationservice.respository.TripRegistrationTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripRegistrationTableServiceImpl implements TripRegistrationTableService {

    private static  final String Topic="registredTrip";
    private static  final String TopicKafka="searchregistredTrip";

    @Autowired
    private KafkaTemplate<String, Trip> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, SearchTripKafka> TripkafkaTemplate;

    @Autowired
    private TripRegistrationTableRepository tripRegistrationTableRepository;

    @Override
    public TripRegistrationTable  saveTrip(TripRegistrationTable tripRegistrationTable) {
        return tripRegistrationTableRepository.save(tripRegistrationTable);
    }

    @Override
    public TripRegistrationTable registrationTableMapper(long driverId, Trip trip) {

        TripRegistrationTable tr=new TripRegistrationTable();

        tr.setTripId(trip.getId());
        tr.setDriverId(driverId);


        //get trip details:
        tr.setPickupPlace(trip.getPickupPlace());
        tr.setDropOffPlace(trip.getDropOffPlace());
        tr.setTripStartingTime(trip.getTripStartingTime());
        tr.setTripEndTime(trip.getTripEndTime());
        tr.setRoundTrip(trip.isRoundTrip());
        tr.setNumberOfAvailableSeats(trip.getNumberOfAvailableSeats());
        tr.setTripPrice(trip.getTripPrice());
        tr.setTripDescription(trip.getTripDescription());
        return tr;

    }

    @Override
    public Trip tripMapper(TripRegistrationTable tr) {

        Trip trip= new Trip();

        trip.setId(tr.getTripId());
        trip.setPickupPlace(tr.getPickupPlace());
        trip.setDropOffPlace(tr.getDropOffPlace());
        trip.setTripStartingTime(tr.getTripStartingTime());
        trip.setTripEndTime(tr.getTripEndTime());
        trip.setRoundTrip(tr.isRoundTrip());
        trip.setNumberOfAvailableSeats(tr.getNumberOfAvailableSeats());
        trip.setTripPrice(tr.getTripPrice());
        trip.setTripDescription(tr.getTripDescription());

        //Create driver for the trip
        User driver = new User();
        driver.setId(tr.getDriverId());
        driver.setFirstName(tr.getFirstName());
        driver.setLastName(tr.getLastName());
        driver.setEmailAddress(tr.getEmailAddress());
        trip.setDriver(driver);

        //creat reservation with total available sets:
        Reservation reservation= new Reservation();
        reservation.setId(tr.getReservationId());
        trip.setReservation(reservation);

        return  trip;

    }

    @Override
    public List<TripRegistrationTable> getAllRegTrips() {
        List<TripRegistrationTable> trips= new ArrayList<>();
        tripRegistrationTableRepository.findAll().forEach(trips::add);
        return trips;
    }


    @Override
    public void publishTripKafka(SearchTripKafka searchTripKafka) {
        TripkafkaTemplate.send(TopicKafka, searchTripKafka);

    }

    @Override
    public void publishTrip(Trip  trip) { kafkaTemplate.send(Topic,trip);
    }

    @Override
    public List<Reservation> listOfReservatoin() {
        return null;
    }

    @Override
    public TripRegistrationTable getTripRegById(long id) {
        return tripRegistrationTableRepository.findById(id).get();
    }

  /*  @Override
    public TripRegistrationTable getTripById(long id) {
        return  tripRegistrationTableRepository.findById(id).get();
    }
*/

}


