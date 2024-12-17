package mk.ukim.finki.wp.lab.service;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;

import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    Event update(Event event);

     Event saveEvent(String name, String description, double popularityScore,Integer numberOfTickets, Long locationid);
     Event updateEvent(Long id,String name, String description, double popularityScore,Integer numberOfTickets, Long locationid);

    Optional<Event> findById(Long id);

    void deleteById(Long id);
    List<Event> findByLocation(Long id);

}
