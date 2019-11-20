package mum.cs.cs544.finalproject.tripregistrationservice.controller;
import com.datastax.driver.core.utils.UUIDs;
import mum.cs.cs544.finalproject.tripregistrationservice.model.Trip;
import mum.cs.cs544.finalproject.tripregistrationservice.model.User;
import mum.cs.cs544.finalproject.tripregistrationservice.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    KafkaTemplate<String, Trip> kafkaTemplate;
    private static  final String Topic="registredTrip";

    @GetMapping("/info")
    public  String  getTripHomeInfo(){
        return "This is Trip home page";
    }


    @PostMapping("/register")
    public  Trip  registerTrip(@RequestBody  Trip trip) throws ValidationException {


        if(trip==null)
            throw  new ValidationException("Trip should be not Null");

        trip.setId(UUIDs.timeBased());

        kafkaTemplate.send(Topic,trip);
       return tripService.saveTrip(trip);
    }



    @GetMapping("/byID/{tripId}")
    public Trip  getTripById(@PathVariable ("tripId")UUID id){
            return tripService.getTripById(id);
}

@GetMapping("/alltrip")
    public List<Trip> getAllTrip(){
return  tripService.getAllTrips();
}

    @GetMapping("/getdriver")
    public User getDriver(){
    return  restTemplate.getForObject("http://registration-service/user/getuser", User.class);
    }
}
