package edu.mum.ea.searchservice.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.ea.searchservice.model.SearchForm;
import edu.mum.ea.searchservice.model.Trip;
import edu.mum.ea.searchservice.model.TripSender;
import edu.mum.ea.searchservice.model.User;
import edu.mum.ea.searchservice.repository.TripRepository;
import edu.mum.ea.searchservice.service.QueryDSLService;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


@RestController
@CrossOrigin(value = "*")
@RequestMapping("/search")
public class SearchController {
    private static final Logger LOG = Logger.getLogger(SearchController.class.getName());
    static DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    
    

    @Autowired
    private TripRepository tripRepo;

    @Autowired 
    private QueryDSLService dslService;


    @PostMapping()
    public List<TripSender> searchManger(@RequestBody SearchForm data){
         

        //find all method 
        if(data.getFrom().equals("") && data.getDate().equals("") && data.getTo().equals("") && data.getNoOfSeats()==null && data.getPriceRangeFrom()==null && data.getPriceRangeUntil()==null){

            List<Trip> tripList = new ArrayList<>();
            Iterable<Trip> tripes = tripRepo.findAll();
            tripes.forEach(tripList::add);
    
            List<TripSender> sender = new ArrayList<>();
            for (Trip trip : tripList) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), trip.getTripStartingTime(), trip.getTripEndTime(), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
                LOG.log(Level.INFO, trip.toString());
            }
            
            return sender;
        }


        //user by pick up place 
        else if(data.getFrom() != null && data.getDate().equals("") && data.getTo().equals("") && data.getNoOfSeats()==null && data.getPriceRangeFrom()==null && data.getPriceRangeUntil()==null){
            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= tripRepo.findByPickUpPlace(data.getFrom());
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), trip.getTripStartingTime(), trip.getTripEndTime(), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;
        }


        //multisearch with pick up places, drop off places and intial date 
        else if(data.getFrom() != "" && data.getTo() != "" && data.getDate() != "" && data.getNoOfSeats()==null && data.getPriceRangeFrom()==null && data.getPriceRangeUntil()==null){

            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= dslService.searchMultiField(data.getFrom(), data.getTo(), data.getDate());
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), trip.getTripStartingTime(), trip.getTripEndTime(), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;

        }


        //user by dropoffplace only 
        else if(data.getFrom().equals("") && data.getTo() != null && data.getDate().equals("") && data.getNoOfSeats()==null && data.getPriceRangeFrom()==null && data.getPriceRangeUntil()==null){

            
            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= dslService.getDestinationPlaceSerachData(data.getTo());
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), trip.getTripStartingTime(), trip.getTripEndTime(), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;

        }

        // any text related to  the locations
        else if((data.getFrom() != null || data.getTo() != null) && data.getDate().equals("") && data.getNoOfSeats()==null && data.getPriceRangeFrom()==null && data.getPriceRangeUntil()==null){

            String text = null;
            if(data.getFrom()==null){
                text = data.getTo();
            }
            else{
                text = data.getFrom();
            }
            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= dslService.multiMatchQuery(text);
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), trip.getTripStartingTime(), trip.getTripEndTime(), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;

        }


         // by price range only
        else if(data.getFrom().equals("") && data.getTo().equals("") && data.getDate().equals("") && data.getNoOfSeats()==null && data.getPriceRangeFrom()!=null && data.getPriceRangeUntil()!=null){

            
            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= dslService.getPriceRangeSearchData(data.getPriceRangeFrom(), data.getPriceRangeUntil());
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), trip.getTripStartingTime(), trip.getTripEndTime(), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;

        }

        //search avilable seat and destination places
        else if(data.getFrom().equals("") && data.getTo() != null && data.getDate().equals("") && data.getNoOfSeats()!=null && data.getPriceRangeFrom()==null && data.getPriceRangeUntil()==null){

            
            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= dslService.getAvilableSeatANDDestination(data.getNoOfSeats(), data.getTo());
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), trip.getTripStartingTime(), trip.getTripEndTime(), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;

        }

        // sarch starting, destination and price range values
        else if(data.getFrom() != null && data.getTo() != null && data.getDate().equals("") && data.getNoOfSeats()==null && data.getPriceRangeFrom()!=null && data.getPriceRangeUntil()!=null){

            
            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= dslService.getFromToPriceRnageSearchData(data.getFrom(), data.getTo(),data.getPriceRangeFrom(),data.getPriceRangeUntil());
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), trip.getTripStartingTime(), trip.getTripEndTime(), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;

        }


        return null;
    } 

    @PostMapping("/saveTrip")
    public int saveTrip(@RequestBody List<Trip> trip){
        for (Trip trips : trip) {
            tripRepo.save(trips);
            LOG.log(Level.INFO, trips.toString());
        }
        return trip.size();
    }

    @GetMapping("/findAll")
    public Iterable<TripSender> finadAllTrip(){
        List<Trip> tripList = new ArrayList<>();
        Iterable<Trip> tripes = tripRepo.findAll();
        tripes.forEach(tripList::add);

        List<TripSender> sender = new ArrayList<>();


        for (Trip trip : tripList) {
            User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
            TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), trip.getTripStartingTime(), trip.getTripEndTime(), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
            sender.add(dd);
            LOG.log(Level.INFO, trip.toString());
        }
        
        return sender;
    }

    @GetMapping("/email/{emailAddress}")
    public Iterable<TripSender> findByEmail(@PathVariable("emailAddress")String emailAddress){
        List<Trip> tripList = new ArrayList<>();
        Iterable<Trip> tripes = tripRepo.findByEmailAddress(emailAddress+".com");
        tripes.forEach(tripList::add);

        List<TripSender> sender = new ArrayList<>();


        for (Trip trip : tripList) {
            User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
            TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), trip.getTripStartingTime(), trip.getTripEndTime(), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
            sender.add(dd);
            LOG.log(Level.INFO, trip.toString());
        }
        
        return sender;
    }

}