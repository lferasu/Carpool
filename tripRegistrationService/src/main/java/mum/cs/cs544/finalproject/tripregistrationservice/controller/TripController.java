package mum.cs.cs544.finalproject.tripregistrationservice.controller;
import mum.cs.cs544.finalproject.tripregistrationservice.model.Trip;
import mum.cs.cs544.finalproject.tripregistrationservice.model.SearchTripKafka;
import mum.cs.cs544.finalproject.tripregistrationservice.model.TripRegistrationTable;

import mum.cs.cs544.finalproject.tripregistrationservice.service.TripRegistrationTableService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.xml.bind.ValidationException;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
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
    //SearchTripKafka data = new SearchTripKafka(trip);
    tripRegistrationTableService.publishTripKafka(new SearchTripKafka(registredTrip));
    return registredTrip ;

    }




/*
    @PostMapping("/register{driverId}")
    public  TripRegistrationTable  registerTrip(@PathVariable ("driverId")long driverId) throws ValidationException {

        TripRegistrationTable tr=tripRegistrationTableService.registrationTableMapper(driverId, trip);

        Trip registredTrip=tripRegistrationTableService.tripMapper(tr);

        tripRegistrationTableService.publishTrip(registredTrip);
        tripRegistrationTableService.publishTripKafka(new SearchTripKafka(registredTrip));

        return tripRegistrationTableService.saveTrip(tr);

    }
*/





    /*  @PostMapping("updatereserv")
    public  TripRegistrationTable updateRegistration(){
        TripRegistrationTable tr= tripRegistrationTableService.registrationTableMapper();

    }
*/
    @GetMapping("/alltrip")
    public List<TripRegistrationTable> getAllTrip(){
return  tripRegistrationTableService.getAllRegTrips();
}

}
