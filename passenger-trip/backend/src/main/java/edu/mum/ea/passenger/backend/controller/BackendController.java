package edu.mum.ea.passenger.backend.controller;

import com.datastax.driver.core.utils.UUIDs;
import edu.mum.ea.passenger.backend.entity.Result;
import edu.mum.ea.passenger.backend.entity.TripEntity;
import edu.mum.ea.passenger.backend.repository.PassengerTripRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/passenger-trip")
@Log4j2
public class BackendController {

    @Autowired
    KafkaTemplate<String, TripEntity> kafkaTemplate;


    private final String TOPIC;

    @Autowired
    private PassengerTripRepository passengerTripRepository;

    public BackendController(@Value("${passenger.trip.kafkaTopic: passenger-trip}") String topic) {
        TOPIC = topic;
    }

    // for K8S Health check
    @GetMapping("/")
    public ResponseEntity<?> index() {
        String host = "Unknown host";
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            log.error(e);
        }
        TripEntity trip = new TripEntity();
        trip.setId(UUID.randomUUID());
        kafkaTemplate.send(TOPIC, trip);
        return new ResponseEntity<>("passenger-trip-service. Host: " + host, HttpStatus.OK);
    }


    /**
     * endpoint for retrieving all trip entries stored in database
     *
     * @return a list of TripEntity objects
     */
    @GetMapping("/list")
    public final List<TripEntity> getTrips() {

        Iterable<TripEntity> result = passengerTripRepository.findAll();
        List<TripEntity> tripList = new ArrayList<TripEntity>();
        result.forEach(tripList::add);
        return tripList;

    }
    @GetMapping("/listBetween")
    public final List<TripEntity> getTripsBetween(@RequestParam LocalDateTime s, @RequestParam LocalDateTime e) {

        Iterable<TripEntity> result = passengerTripRepository.
                findTripEntitiesByArrivalDateTimeEndIsLessThanAndArrivalDateTimeStartGreaterThan(s,e);
        List<TripEntity> tripList = new ArrayList<TripEntity>();
        result.forEach(tripList::add);
        return tripList;

    }


    // accessible without authentication
    @GetMapping("/get/{id}")
    public ResponseEntity<TripEntity> getTrip(@PathVariable(name = "id") UUID id) {
        TripEntity entity = passengerTripRepository.findById(id).orElse(null);
        if (entity == null) return ResponseEntity.noContent().build();
        log.info(entity);
        return ResponseEntity.ok(entity);
    }


    /**
     * endpoint for adding a new trip entry to the database
     *
     * @param trip a TripEntity object passed in the HTTP POST request
     */
    @PostMapping("/add")
    public final ResponseEntity<Result> addTrip(@RequestBody TripEntity trip) {
        trip.setId(UUIDs.timeBased());
        trip.setTimeStamp(System.currentTimeMillis());
        try {
            passengerTripRepository.save(trip);
        } catch (Exception ex) {
            return ResponseEntity.ok().body(new Result(false, ex.getMessage()));
        }
        log.info("Saved trip {}", trip);
        kafkaTemplate.send(TOPIC, trip);
        return ResponseEntity.ok().body(new Result(true, "Saved successfully"));
    }

    @PutMapping("/update/{id}")
    public final ResponseEntity<Result> updateTripEntity(@RequestBody TripEntity newTripEntity, @PathVariable UUID id) {
        Optional<TripEntity> optionalTrip = passengerTripRepository.findById(id);
        if (optionalTrip.isPresent()) {
//            TripEntity trip = optionalTrip.get();
            newTripEntity.setId(id);
            try {
                passengerTripRepository.save(newTripEntity);
            } catch (Exception ex) {
                return ResponseEntity.ok().body(new Result(false, ex.getMessage()));
            }
            return ResponseEntity.ok().body(new Result(true, "Update successfully"));
        }
        return ResponseEntity.ok().body(new Result(false, "No record for id: " + id));
    }

    // authentication required
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> deleteProduct(@PathVariable(name = "id") UUID id) {
        try {
            passengerTripRepository.deleteById(id);
        } catch (Exception ex) {
            return ResponseEntity.ok().body(new Result(false, ex.getMessage()));
        }
        return ResponseEntity.ok().body(new Result(true, "Deleted successfully"));
    }


}
