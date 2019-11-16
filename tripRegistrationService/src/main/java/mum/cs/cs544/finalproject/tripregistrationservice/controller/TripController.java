package mum.cs.cs544.finalproject.tripregistrationservice.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class TripController {
    @GetMapping("/info")
    public  String  getTripHomeInfo(){
        return "This is Trip home page";
    }
}
