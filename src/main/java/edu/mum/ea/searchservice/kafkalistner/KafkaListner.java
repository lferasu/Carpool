package edu.mum.ea.searchservice.kafkalistner;


import edu.mum.ea.searchservice.model.Trip;
import edu.mum.ea.searchservice.model.TripData;
import edu.mum.ea.searchservice.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class KafkaListner {

    @Autowired
    private TripRepository tripRepo;

    @KafkaListener(topics = "registredTrip", groupId = "group_id")
    public void consumeTrip (@Payload TripData trip,
                             @Headers MessageHeaders headers)  {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        CharSequence cs = trip.getTripStartingTime();
        LocalDateTime dateTimeStarting =  LocalDateTime.parse(cs,formatter);
        LocalDate staDate = dateTimeStarting.toLocalDate();
        LocalDateTime dateTimeEnding = LocalDateTime.parse(trip.getTripStartingTime(), formatter);
        LocalDate endDate = dateTimeEnding.toLocalDate();
        int timeNumber = staDate.getDayOfMonth();

        Trip tt = new Trip(trip.getId(), trip.getPickupPlace(), trip.getDropOffPlace(), staDate.toString(), endDate.toString(), trip.isIsRoundTrip(), trip.getNumberOfAvailableSeats(), trip.getTripPrice(), trip.getTripDescription(), trip.getDriver().getFirstName(), trip.getDriver().getLastName(), trip.getDriver().getEmail(), timeNumber, trip.getDriver().getId(), trip.getDriver().getAdrState(), trip.getDriver().getAdrCity(), trip.getDriver().getAdrStreet(), trip.getDriver().getAdrZip());
       // Trip tt = new Trip(trip.getId(),trip.getPickupPlace(),trip.getDropOffPlace(),staDate.toString(),endDate.toString(),);

        tripRepo.save(tt);
        //System.out.println(trip);
        System.out.println(tt);

        }

    }
