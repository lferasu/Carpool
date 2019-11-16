package mum.cs.cs544.finalproject.tripregistrationservice.controller;
import com.datastax.driver.core.utils.UUIDs;
import mum.cs.cs544.finalproject.tripregistrationservice.model.Trip;
import mum.cs.cs544.finalproject.tripregistrationservice.model.User;
import mum.cs.cs544.finalproject.tripregistrationservice.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;


    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/info")
    public  String  getTripHomeInfo(){
        return "This is Trip home page";
    }


    @PostMapping("/register")
    public  Trip  registerTrip(@RequestBody  Trip trip){
        trip.setId(UUIDs.timeBased());
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
