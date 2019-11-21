package edu.mum.ea.searchservice.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import edu.mum.ea.searchservice.model.*;

@Service
public class QueryDSLService {

    private static final Logger LOG = Logger.getLogger(QueryDSLService.class.getName());


    @Value("${elasticsearch.index.name}")
    private String indexName;

    @Value("${elasticsearch.user.type}")
    private String userTypeName;


	@Autowired
	private ElasticsearchTemplate template;


    // any search result with starting, destination places and date  
	public List<Trip> searchMultiField(String pickUpPlace, String dropOffPlace, String tripStartingTime) {
        QueryBuilder query = QueryBuilders.boolQuery()
                            .must(QueryBuilders.matchQuery("pickUpPlace", pickUpPlace))
                            .must(QueryBuilders.matchQuery("dropOffPlace", dropOffPlace))
                            .must(QueryBuilders.matchQuery("tripStartingTime", tripStartingTime));
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        List<Trip> users = template.queryForList(nativeSearchQuery, Trip.class);
        LOG.log(Level.INFO, users);
		return users;
	}


    @Cacheable("trip")
    // adding some caching here
    // any search result that is related to destination search places  
	public List<Trip> getDestinationPlaceSerachData(String input) {
        sleep();
		String search = ".*" + input + ".*";
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withFilter(QueryBuilders.regexpQuery("dropOffPlace", search)).build();
        List<Trip> users = template.queryForList(searchQuery, Trip.class);
        LOG.log(Level.INFO, users);
		return users; 

	}

    @Cacheable("trip")
    //find places on either of the destination and satring places
	public List<Trip> multiMatchQuery(String text) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.multiMatchQuery(text)
				.field("pickUpPlace").field("dropOffPlace").type(MultiMatchQueryBuilder.Type.BEST_FIELDS)).build();
        List<Trip> users = template.queryForList(searchQuery, Trip.class);
        LOG.log(Level.INFO, users);
		return users;
    }

    @Cacheable("trip")
    // search result with price range  
    public List<Trip> getPriceRangeSearchData(Double minmiumPrice, Double maximumPrice){
        QueryBuilder query = QueryBuilders.rangeQuery("tripPrice").from(minmiumPrice).to(maximumPrice).includeLower(true).includeUpper(false);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        List<Trip> users = template.queryForList(nativeSearchQuery, Trip.class);
        LOG.log(Level.INFO, users);
		return users;
    }


    @Cacheable("trip")
    //search avilable seat and destination places 
    public List<Trip> getAvilableSeatANDDestination(Integer numberOfAvilableSeats, String dropOffPlace){
        LocalDate present = LocalDate.now();
        int todayNumber = present.getDayOfMonth();
        BoolQueryBuilder query = QueryBuilders.boolQuery()
                                    .must(QueryBuilders.rangeQuery("numberOfAvilableSeats").gte(numberOfAvilableSeats))
                                    .must(QueryBuilders.rangeQuery("numberDate").gte(todayNumber))
                                    .must(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("dropOffPlace", dropOffPlace)));
         NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
         List<Trip> users = template.queryForList(nativeSearchQuery, Trip.class);
         LOG.log(Level.INFO, users);
        return users;
    }


    @Cacheable("trip")
    // sarch starting, destination and price range values
    public List<Trip> getFromToPriceRnageSearchData(String pickUpPlace, String dropOffPlace, Double intialPrice, Double finalPrice ){

        BoolQueryBuilder query = QueryBuilders.boolQuery()
                                .must(QueryBuilders.rangeQuery("tripPrice").gte(intialPrice))
                                .must(QueryBuilders.rangeQuery("tripPrice").lte(finalPrice))
                                .must(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("pickUpPlace", pickUpPlace)))
                                .must(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("dropOffPlace", dropOffPlace)));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        List<Trip> users = template.queryForList(nativeSearchQuery, Trip.class);
        LOG.log(Level.INFO, users);
        return users;

    }


    @Cacheable("trip")
    // sarch starting, destination and date range values
    public List<Trip> getFromToDateRnageSearchData(String pickUpPlace, String dropOffPlace, String tripStartingTime, String tripEndTime ){
        String str1[] = tripStartingTime.split("-");
        int day1 = Integer.parseInt(str1[2]);
        String str2[] = tripEndTime.split("-");
        int day2 = Integer.parseInt(str2[2]);
        BoolQueryBuilder query = QueryBuilders.boolQuery()
                                .must(QueryBuilders.rangeQuery("numberDate").gte(day1))
                                .must(QueryBuilders.rangeQuery("numberDate").lte(day2))
                                .must(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("pickUpPlace", pickUpPlace)))
                                .must(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("dropOffPlace", dropOffPlace)));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        List<Trip> users = template.queryForList(nativeSearchQuery, Trip.class);
        LOG.log(Level.INFO, users);
        return users;

    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}