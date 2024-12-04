package mk.ukim.finki.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exceptions.LocationException;

import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository repository, LocationRepository locationRepository) {
        this.repository = repository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return repository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return repository.searchAllByNameOrDescriptionContaining(text, text);
    }

    @Override
    @Transactional
    public Event update(Event event) {
        Optional<Event> ev = repository.findById(event.getId());
        if (ev.isPresent()) {
            event = ev.get();
            event.setNumTickets(event.getNumTickets());
        }
        return repository.save(event);
    }

    public Event updateAll(Event event) {
        Optional<Event> ev = repository.findById(event.getId());
        if (ev.isPresent()) {
            event = ev.get();
            event.setNumTickets(event.getNumTickets());
        }
        return repository.save(event);
    }

    @Override
    @Transactional
    public Event saveEvent(String name, String description, double popularityScore, Integer numberOfTickets, Long locationid) {
        Location location = locationRepository.findById(locationid).orElseThrow(() -> new LocationException(locationid));
        ;
        Event event = new Event(name, description, popularityScore, numberOfTickets, location);
        this.repository.deleteByName(event.getName());
        return repository.save(event);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Event> findByLocation(Long id) {
        return this.repository.findAllByLocation_id(id);
    }
}
