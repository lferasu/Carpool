package mum.cs.cs544.finalproject.tripregistrationservice.controller;
import mum.cs.cs544.finalproject.tripregistrationservice.model.SearchTripKafka;
import mum.cs.cs544.finalproject.tripregistrationservice.model.Trip;
import mum.cs.cs544.finalproject.tripregistrationservice.model.TripRegistrationTable;
import mum.cs.cs544.finalproject.tripregistrationservice.service.TripRegistrationTableService;
import mum.cs.cs544.finalproject.tripregistrationservice.service.TripRegistrationTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReserveListener {

    @Autowired
    TripRegistrationTableService tripRegistrationTableService;
    // private EmailConfiguration emailConfiguration;


    @KafkaListener(topics = "${kafka.topic.reservation}", groupId = "group_id")
    public void consumeTrip (@Payload Trip trip,
                             @Headers MessageHeaders headers){
        long driverId= trip.getDriver().getId();
        int avaliableSet= trip.getNumberOfAvailableSeats();

        if(avaliableSet>0){
            long tripId=trip.getId();
            TripRegistrationTable tripRegistrationTable=tripRegistrationTableService.getTripRegById(tripId);
            tripRegistrationTable.setNumberOfAvailableSeats(avaliableSet);

            //save to database

            TripRegistrationTable savedrt = tripRegistrationTableService.saveTrip(tripRegistrationTable);
            publishreservedTripAgain(savedrt);

        }

        }


        public  Trip publishreservedTripAgain(TripRegistrationTable rt){


            Trip registredTrip=tripRegistrationTableService.tripMapper(rt);
            //SearchTripKafka sk=new SearchTripKafka(registredTrip);

            tripRegistrationTableService.publishTrip(registredTrip);
            //SearchTripKafka data = new SearchTripKafka(trip);
            tripRegistrationTableService.publishTripKafka(new SearchTripKafka(registredTrip));
            return registredTrip ;

    }

}
