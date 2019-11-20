package mum.cs.cs544.finalproject.tripregistrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TripregistrationServiceApplication {

    @Bean

    public RestTemplate getResource(){
        return  new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(TripregistrationServiceApplication.class, args);
    }

}
