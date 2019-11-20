package edu.mum.ea.passenger.frontend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<?> index() {
        String host = "Unknown host";
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("passenger-trip-front-service. Host: " + host, HttpStatus.OK);
    }

//    @Value("${passenger-secret}")
//    private String serviceSecret;

    @GetMapping("/test")
    public String firstPage() {
        return "Ok. You authenticated user";
    }

}
