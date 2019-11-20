package edu.mum.ea.passenger.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendPassengerApplication {

        public static void main(String[] args) {
            final String[] expectedVariables = {"PORT", "PASSENGER_TRIP_DB_ADDR"};
            for (String v : expectedVariables) {
                String value = System.getenv(v);
                if (value == null) {
                    System.out.format("error: %s environment variable not set", v);
                    System.exit(1);
                }
            }
            SpringApplication.run(edu.mum.ea.passenger.backend.BackendPassengerApplication.class, args);
        }
}
