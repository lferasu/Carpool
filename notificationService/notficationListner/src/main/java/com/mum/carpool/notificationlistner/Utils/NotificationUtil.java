package com.mum.carpool.notificationlistner.Utils;

//import com.mum.carpool.notificationlistner.config.EmailConfiguration;
import com.mum.carpool.notificationlistner.model.Notification;
import com.mum.carpool.notificationlistner.model.Reservation;
import com.mum.carpool.notificationlistner.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class NotificationUtil {

    public static List<Notification> extractNotification(Trip trip) {
        Reservation lastReservation = trip.getReservation();
        int noOfseat =  lastReservation.getNoOfReservedSeats();

        List<Notification> notifications = new ArrayList<>();
        notifications.addAll(extractDriverNotification(trip, lastReservation));
        notifications.add(extractReserverNotification(trip,lastReservation));
        return notifications;
    }


    private static List<Notification> extractDriverNotification(Trip trip , Reservation lastReservation) {
        List<Notification> notifications = new ArrayList<>();
        int noOfseat =  lastReservation.getNoOfReservedSeats();
        String reserver = lastReservation.getReservedBy().getUsername();
        String notificationMessage = "New Reservation has been placed by " +reserver + " for " + noOfseat + " seats";

      notifications.add(new Notification(trip.getDriver().getUsername(),
              trip.getDriver().getEmailAddress(),
              notificationMessage, "New Reservation")) ;

        if(trip.getNumberOfAvailableSeats() == 0) {
            ;
            notifications.add(new Notification(trip.getDriver().getUsername(),
                    trip.getDriver().getEmailAddress(),
                    "Ur trip is full and ready to depart ",
                    "Trip Ready")) ;
        }
        return  notifications;
    }

    private static Notification extractReserverNotification(Trip trip, Reservation lastReservation) {
        int noOfseat =  lastReservation.getNoOfReservedSeats();

       return new Notification(lastReservation.getReservedBy().getUsername(),
               lastReservation.getReservedBy().getEmailAddress(),
                "Your reservation is confirmed for " + lastReservation.getNoOfReservedSeats() + " seats",
               "Reservation Confirmation");
    }

}
