package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

@Repository
public class EventBookingRepository {

    public EventBooking addNew(EventBooking eventBooked){
         DataHolder.eventsBooked.add(eventBooked);
         return eventBooked;
    }



}
