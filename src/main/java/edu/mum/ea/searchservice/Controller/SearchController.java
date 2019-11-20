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
    

    @Autowired
    private TripRepository tripRepo;

    @Autowired 
    private QueryDSLService dslService;

    // @Autowired
	// private Client client;



    @GetMapping()
    public List<TripSender> searchManger(@RequestBody SearchForm data){
         


        if(data.getFrom() ==null && data.getDate().equals(null) && data.getTo().equals(null) && data.getNoOfSeats()==0 && data.getPriceRangeFrom()==0 && data.getPriceRangeUntil()==0){

            List<Trip> tripList = new ArrayList<>();
            Iterable<Trip> tripes = tripRepo.findAll();
            tripes.forEach(tripList::add);
            // for (Trip trip : tripList) {
            //     LOG.log(Level.INFO, trip.toString());
            // }
            List<TripSender> sender = new ArrayList<>();
            for (Trip trip : tripList) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), LocalDateTime.parse(trip.getTripStartingTime()), LocalDateTime.parse(trip.getTripEndTime()), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
                LOG.log(Level.INFO, trip.toString());
            }
            
            return sender;
        }

        else if(data.getFrom() !=null && data.getDate().equals(null) && data.getTo().equals(null) && data.getNoOfSeats()==0 && data.getPriceRangeFrom()==0 && data.getPriceRangeUntil()==0){
            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= tripRepo.findByPickUpPlace(data.getFrom());
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), LocalDateTime.parse(trip.getTripStartingTime()), LocalDateTime.parse(trip.getTripEndTime()), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;
        }



        else if(data.getFrom() != null && data.getTo() != null && data.getDate() != null && data.getNoOfSeats()==0 && data.getPriceRangeFrom()==0 && data.getPriceRangeUntil()==0){

            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= dslService.searchMultiField(data.getFrom(), data.getTo(), data.getDate().format(formatterTime));
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), LocalDateTime.parse(trip.getTripStartingTime()), LocalDateTime.parse(trip.getTripEndTime()), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;

        }



        else if(data.getFrom() == null && data.getTo() != null && data.getDate() == null && data.getNoOfSeats()==0 && data.getPriceRangeFrom()==0 && data.getPriceRangeUntil()==0){

            
            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= dslService.getDestinationPlaceSerachData(data.getTo());
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), LocalDateTime.parse(trip.getTripStartingTime()), LocalDateTime.parse(trip.getTripEndTime()), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;

        }


        else if((data.getFrom() != null || data.getTo() != null) && data.getDate() == null && data.getNoOfSeats()==0 && data.getPriceRangeFrom()==0 && data.getPriceRangeUntil()==0){

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
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), LocalDateTime.parse(trip.getTripStartingTime()), LocalDateTime.parse(trip.getTripEndTime()), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;

        }



        else if(data.getFrom() == null && data.getTo() == null && data.getDate() == null && data.getNoOfSeats()==0 && data.getPriceRangeFrom()!=0 && data.getPriceRangeUntil()!=0){

            
            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= dslService.getPriceRangeSearchData(data.getPriceRangeFrom(), data.getPriceRangeUntil());
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), LocalDateTime.parse(trip.getTripStartingTime()), LocalDateTime.parse(trip.getTripEndTime()), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;

        }


        else if(data.getFrom() == null && data.getTo() != null && data.getDate() == null && data.getNoOfSeats()!=0 && data.getPriceRangeFrom()==0 && data.getPriceRangeUntil()==0){

            
            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= dslService.getAvilableSeatANDDestination(data.getNoOfSeats(), data.getTo());
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), LocalDateTime.parse(trip.getTripStartingTime()), LocalDateTime.parse(trip.getTripEndTime()), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
                sender.add(dd);
            }
            
            return sender;

        }

        else if(data.getFrom() != null && data.getTo() != null && data.getDate() == null && data.getNoOfSeats()==0 && data.getPriceRangeFrom()!=0 && data.getPriceRangeUntil()!=0){

            
            List<TripSender> sender = new ArrayList<>();
            List<Trip> s= dslService.getFromToPriceRnageSearchData(data.getFrom(), data.getTo(),data.getPriceRangeFrom(),data.getPriceRangeUntil());
            for (Trip trip : s) {
                User driver = new User(trip.getDriverId(), trip.getFirstName(), trip.getLastName(), trip.getEmailAddress(), trip.getAdrStreet(), trip.getAdrCity(), trip.getAdrState(), trip.getAdrZip());
                TripSender dd = new TripSender(trip.getId(), trip.getPickUpPlace(), trip.getDropOffPlace(), LocalDateTime.parse(trip.getTripStartingTime()), LocalDateTime.parse(trip.getTripEndTime()), trip.isIsRoundTrip(), trip.getNumberOfAvilableSeats(), 0, trip.getTripPrice(), trip.getTripDescription(), driver, null);
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


    // @PostMapping("/create")
    // public String create(@RequestBody Trip user) throws IOException {
    //     IndexResponse response = client.prepareIndex("tfr", "tasd", user.getId())
    //             .setSource(jsonBuilder()
    //                     .startObject()
    //                     .field("pickUpPlace", user.getPickUpPlace())
    //                     .field("dropOffPlace", user.getDropOffPlace())
    //                     .endObject()
    //             )
    //             .get();
    //             tripRepo.save(user);
    //            System.out.println("response id:"+response.getId());
    //     return "added Succesffully ";
    // }

    // @GetMapping("/view/{id}")
    // public Map<String, Object> view(@PathVariable final String id) {
    //     GetResponse getResponse = client.prepareGet("tfr", "tasd", id).get();
    //     System.out.println(getResponse.getSource());


    //     return getResponse.getSource();
    // }

    @GetMapping("/findBypickUpPlace/{pickUpPlace}")
    public List<Trip> findByFirstName(@PathVariable("pickUpPlace") String pickUpPlace){
        List<Trip> s= tripRepo.findByPickUpPlace(pickUpPlace);
        LOG.log(Level.INFO, s.toString());
        return s;
    }

    @GetMapping("/findAll")
    public Iterable<Trip> finadAllTrip(){
        List<Trip> tripList = new ArrayList<>();
        Iterable<Trip> tripes = tripRepo.findAll();
        tripes.forEach(tripList::add);
        for (Trip trip : tripList) {
            LOG.log(Level.INFO, trip.toString());
        }
        return tripList;
    }

    //multisearch with satrting,ending and intial date 
    @GetMapping("/serachMultiField/{pickUpPlace}/{dropOffPlace}/{tripStartingTime}")
	public List<Trip> serachByMultiField(@PathVariable String pickUpPlace, @PathVariable String dropOffPlace, @PathVariable String tripStartingTime) {
		return dslService.searchMultiField(pickUpPlace, dropOffPlace, tripStartingTime);
    }
    
    //user by dropoffplace
    @GetMapping("/placeSearch/{dropOffPlace}")
	public List<Trip> getUserByDropOffPlace(@PathVariable String dropOffPlace) {
		return dslService.getDestinationPlaceSerachData(dropOffPlace);
	}

    // any text on the locations
	@GetMapping("/search/{text}")
	public List<Trip> doMultimatchQuery(@PathVariable String text) {
		return dslService.multiMatchQuery(text);
    }
    
    // by price range only 
    @GetMapping("/serachPriceRange/{minmiumPrice}/{maximumPrice}")
	public List<Trip> serachByPriceRange(@PathVariable Double minmiumPrice, @PathVariable Double maximumPrice) {
		return dslService.getPriceRangeSearchData(minmiumPrice, maximumPrice);
    }


    //search avilable seat and destination places
    @GetMapping("/serachPriceAndAvilableSeat/{numberOfAvilableSeats}/{dropOffPlace}")
	public List<Trip> serachByPriceAvilableSeat(@PathVariable Integer numberOfAvilableSeats, @PathVariable String dropOffPlace) {
		return dslService.getAvilableSeatANDDestination(numberOfAvilableSeats, dropOffPlace);
    }


    // sarch starting, destination and price range values
    @GetMapping("/serachPriceLocations/{pickUpPlace}/{dropOffPlace}/{intialPrice}/{finalPrice}")
	public List<Trip> serachByPriceRangeLocation(@PathVariable String pickUpPlace, @PathVariable String dropOffPlace, @PathVariable Double intialPrice, @PathVariable Double finalPrice ) {
		return dslService.getFromToPriceRnageSearchData(pickUpPlace, dropOffPlace,intialPrice,finalPrice);
    }


     // sarch starting, destination and date range values
     @GetMapping("/serachDateLocations/{pickUpPlace}/{dropOffPlace}/{tripStartingTime}/{tripEndTime}")
     public List<Trip> serachByPriceRangeDate(@PathVariable String pickUpPlace, @PathVariable String dropOffPlace, @PathVariable String tripStartingTime, @PathVariable String tripEndTime ) {
         return dslService.getFromToDateRnageSearchData(pickUpPlace, dropOffPlace,tripStartingTime,tripEndTime);
     }


}