package com.mum.carpool.notificationproducer.mocknotificationproducer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController("/")
public class controller {

    @GetMapping
    public List<Trip> getAll() {

        Trip t1 = new Trip();
        Trip t2 = new Trip();
        Trip t3 = new Trip();
        Trip t4 = new Trip();
        Trip t5 = new Trip();
        Trip t6 = new Trip();
        Trip t7 = new Trip();
        Trip t8 = new Trip();

        t1.setId(1L);
        t2.setId(2L);
        t3.setId(3L);
        t4.setId(4L);
        t5.setId(5L);
        t6.setId(6L);
        t7.setId(7L);
        t8.setId(8L);





        t1.setDropOffPlace("haha");
        t1.setPickupPlace("hoo");

        t2.setDropOffPlace("haha");
        t2.setPickupPlace("hoo");
        t3.setDropOffPlace("haha");
        t3.setPickupPlace("hoo");
        t4.setDropOffPlace("haha");
        t4.setPickupPlace("hoo");

        t1.setTripPrice(20.0);
        t2.setTripPrice(20.0);t3.setTripPrice(20.0);t4.setTripPrice(20.0);
        t1.setTripStartingTime(LocalDateTime.now());
        t2.setTripStartingTime(LocalDateTime.now());
        t3.setTripStartingTime(LocalDateTime.now());
        t4.setTripStartingTime(LocalDateTime.now());
        t1.setNumberOfAvailableSeats(2);
        t2.setNumberOfAvailableSeats(2);
        t3.setNumberOfAvailableSeats(2);
        t4.setNumberOfAvailableSeats(2);

        t1.setTripDescription("this is my description");
        t2.setTripDescription("this is my description");
        t3.setTripDescription("this is my description");
        t4.setTripDescription("this is my description");


        /////


        t5.setDropOffPlace("haha");
        t5.setPickupPlace("hoo");

        t6.setDropOffPlace("haha");
        t6.setPickupPlace("hoo");
        t7.setDropOffPlace("haha");
        t7.setPickupPlace("hoo");
        t8.setDropOffPlace("haha");
        t8.setPickupPlace("hoo");

        t5.setTripPrice(20.0);
        t6.setTripPrice(20.0);t7.setTripPrice(20.0);t8.setTripPrice(20.0);
        t5.setTripStartingTime(LocalDateTime.now());
        t6.setTripStartingTime(LocalDateTime.now());
        t7.setTripStartingTime(LocalDateTime.now());
        t8.setTripStartingTime(LocalDateTime.now());
        t5.setNumberOfAvailableSeats(2);
        t6.setNumberOfAvailableSeats(2);
        t7.setNumberOfAvailableSeats(2);
        t8.setNumberOfAvailableSeats(2);

        t5.setTripDescription("this is my description");
        t6.setTripDescription("this is my description");
        t7.setTripDescription("this is my description");
        t8.setTripDescription("this is my description");




        System.out.println(t1.toString());
        return Arrays.asList(t1,t2,t3,t4,t5,t6,t7,t8);
    }

    @GetMapping("/search")
    public List<Trip> getSearched() {

        Trip t1 = new Trip();
        Trip t2 = new Trip();
        Trip t3 = new Trip();
        Trip t4 = new Trip();


        t1.setId(1L);
        t2.setId(2L);
        t3.setId(3L);
        t4.setId(4L);






        t1.setDropOffPlace("haha");
        t1.setPickupPlace("hoo");

        t2.setDropOffPlace("haha");
        t2.setPickupPlace("hoo");
        t3.setDropOffPlace("haha");
        t3.setPickupPlace("hoo");
        t4.setDropOffPlace("haha");
        t4.setPickupPlace("hoo");

        t1.setTripPrice(20.0);
        t2.setTripPrice(20.0);t3.setTripPrice(20.0);t4.setTripPrice(20.0);
        t1.setTripStartingTime(LocalDateTime.now());
        t2.setTripStartingTime(LocalDateTime.now());
        t3.setTripStartingTime(LocalDateTime.now());
        t4.setTripStartingTime(LocalDateTime.now());
        t1.setNumberOfAvailableSeats(2);
        t2.setNumberOfAvailableSeats(2);
        t3.setNumberOfAvailableSeats(2);
        t4.setNumberOfAvailableSeats(2);

        t1.setTripDescription("this is my description");
        t2.setTripDescription("this is my description");
        t3.setTripDescription("this is my description");
        t4.setTripDescription("this is my description");


        /////
        System.out.println(t1.toString());
        return Arrays.asList(t1,t2,t3,t4);
    }
}
