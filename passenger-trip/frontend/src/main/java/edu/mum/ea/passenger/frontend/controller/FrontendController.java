package edu.mum.ea.passenger.frontend.controller;
import edu.mum.ea.passenger.frontend.entity.TripEntity;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

/**
 * defines the REST endpoints managed by the server.
 */
@Controller@RequestMapping(value = "/passenger-trip")@Log4j2
public class FrontendController {

    private String backendUri = String.format("http://%s/passenger-trip",
        System.getenv("PASSENGER_TRIP_API_ADDR"));

    /**
     * endpoint for the landing page
     * @param model defines model for html template
     * @return the name of the html template to render
     */
    @GetMapping("/")
    public final String main(final Model model) {
        RestTemplate restTemplate = new RestTemplate();
        TripEntity[] response = null;
        try {
            response = restTemplate.getForObject(backendUri + "/list",
                    TripEntity[].class);
        }catch (Exception ex){
            log.warn(ex.getMessage());
        }
        model.addAttribute("messages", response);
        return "home";
    }

    /**
     * endpoint for handling form submission
     * @param formMessage holds date entered in html form
     * @return redirects back to home page
     * @throws URISyntaxException when there is an issue with the backend uri
     */
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public final String post(final TripEntity formMessage)
            throws URISyntaxException {
        URI url = new URI(backendUri+"/add");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        HttpEntity<TripEntity> httpEntity =
            new HttpEntity<TripEntity>(formMessage, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, httpEntity, String.class);

        return "redirect:";
    }

}
