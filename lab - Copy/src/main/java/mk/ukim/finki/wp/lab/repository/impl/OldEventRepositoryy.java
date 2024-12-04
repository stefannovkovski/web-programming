package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OldEventRepositoryy {

    public List<Event> findAll(){
        return DataHolder.events;
    }
    public List<Event> searchEvents(String text) {
        return DataHolder.events.stream().filter(r -> r.getName().toLowerCase().contains(text.toLowerCase()) ||
                r.getDescription().toLowerCase().contains(text.toLowerCase())).toList();
    }
    public Event update(Event event) {
        Optional<Event> eventOptional = DataHolder.events.stream()
                .filter(ev -> ev.getName().equals(event.getName()))
                .findFirst();

        if (eventOptional.isPresent()) {
            Event ev = eventOptional.get();
            ev.setNumTickets(event.getNumTickets());
            return ev;
        }

        return null;
    }

    public Event save(Event event){
        DataHolder.events.removeIf(i->i.getName().equals(event.getName()));
        DataHolder.events.add(event);
        return event;
    }
    public Optional<Event> findById(Long id){
        return DataHolder.events.stream().filter(i->i.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id){
        DataHolder.events.removeIf(i->i.getId().equals(id));
    }



}
