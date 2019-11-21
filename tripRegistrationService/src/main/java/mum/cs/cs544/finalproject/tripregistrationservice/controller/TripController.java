package mum.cs.cs544.finalproject.tripregistrationservice.controller;
import mum.cs.cs544.finalproject.tripregistrationservice.model.Trip;
import mum.cs.cs544.finalproject.tripregistrationservice.model.SearchTripKafka;
import mum.cs.cs544.finalproject.tripregistrationservice.model.TripRegistrationTable;

import mum.cs.cs544.finalproject.tripregistrationservice.service.TripRegistrationTableService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.ValidationException;
//import javax.xml.bind.ValidationException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/trip")
public class TripController {

    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Autowired
    private TripRegistrationTableService tripRegistrationTableService;


    @Autowired
    private RestTemplate restTemplate;

    private static  final String Topic="registredTrip";

    @GetMapping("/info")
    public  String  getTripHomeInfo(){
        return "This is Trip home page";
    }


    @PostMapping("/register/{driverId}")
    public  Trip registerTrip(@PathVariable("driverId") long driverId, @RequestBody Trip trip) throws ValidationException {
    TripRegistrationTable tr=tripRegistrationTableService.registrationTableMapper(driverId, trip);

    TripRegistrationTable savedtr=tripRegistrationTableService.saveTrip(tr);

    Trip registredTrip=tripRegistrationTableService.tripMapper(savedtr);
    //SearchTripKafka sk=new SearchTripKafka(registredTrip);

    tripRegistrationTableService.publishTrip(registredTrip);
    SearchTripKafka data = new SearchTripKafka(trip);

    tripRegistrationTableService.publishTripKafka(data);
    return registredTrip ;
    }

    @GetMapping("/alltrip")
    public List<Trip> getAllTrip(){
        List<Trip> trips= new ArrayList<>();
        List<TripRegistrationTable>tripRegistrationTables= tripRegistrationTableService.getAllRegTrips();
        for(TripRegistrationTable tr:tripRegistrationTables){
            Trip trip= tripRegistrationTableService.tripMapper(tr);
            trips.add(trip);
        }
        return trips;
    }

}