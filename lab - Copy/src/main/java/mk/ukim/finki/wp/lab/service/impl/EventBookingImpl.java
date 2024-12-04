package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.impl.EventBookingRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingImpl implements EventBookingService {

    public final EventBookingRepository repository;

    public EventBookingImpl(EventBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return repository.addNew(new EventBooking(eventName,attendeeName,attendeeAddress, (long) numberOfTickets));
    }

}
