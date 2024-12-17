package mk.ukim.finki.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Comment;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exceptions.EventAlreadyExists;
import mk.ukim.finki.wp.lab.model.exceptions.LocationException;

import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    @Transactional
    public Event saveEvent(String name, String description, double popularityScore, Integer numberOfTickets, Long locationid) {
        Location location = locationRepository.findById(locationid).orElseThrow(() -> new LocationException(locationid));

        if (repository.findByNameAndDescription(name,description).isPresent())
        {
            throw new EventAlreadyExists();
        }
        Event event = new Event(name, description, popularityScore, numberOfTickets, location);
        repository.deleteByName(name);
        return repository.save(event);
    }

    @Override
    public Event updateEvent(Long id, String eventName, String eventDescription, double popularityScore, Integer numberTickets, Long locationId) {
        Optional<Location> location = locationRepository.findById(locationId);
        if(location.isPresent()) {
            Event event;
            if (repository.findById(id).isPresent()) {
                List<Comment> commentList = new ArrayList<>();
                if (repository.findById(id).isPresent()) {
                    commentList = List.copyOf(repository.findById(id).get().getComments());
                }
                event = new Event(eventName, eventDescription, popularityScore, numberTickets, location.get(), commentList);

                for (Comment comment : commentList) {
                    comment.setEvent(event);
                }
            } else {
                event = new Event(eventName, eventDescription, popularityScore, numberTickets, location.get());
            }
            repository.deleteById(id);
            return repository.save(event);
        } else {
            throw new RuntimeException("LOCATION EXCEPTION");
        }
    }

    @Override
    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Event> findByLocation(Long id) {
        return this.repository.findAllByLocation_id(id);
    }
}
