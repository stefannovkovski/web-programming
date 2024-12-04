package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = null;
    public static List<EventBooking> eventsBooked = null;
    public static List<Location> locations = null;


    public static List<User> users = null;


    @PostConstruct
    public void init(){
        events=new ArrayList<>();
        locations=new ArrayList<>();

        locations.add(new Location("BigCity","ul.13/br.11","10","club"));
        locations.add(new Location("Zero","ul.17/br.11","100","coffe shop"));
        locations.add(new Location("Bye","ul.13/br.14","50","club"));
        locations.add(new Location("One","ul.3/br.15","70","hotel"));
        locations.add(new Location("Two","ul.12/br.1","90","hotel"));

        events.add(new Event("Event1","party",2.0,5,locations.get(0)));
        events.add(new Event("Event2","party",4.0,3,locations.get(4)));
        events.add(new Event("Event3","charity",3.0,1,locations.get(3)));
        events.add(new Event("Event4","charity",2.3,6,locations.get(0)));
        events.add(new Event("Event5","meeting",2.1,7,locations.get(1)));
        events.add(new Event("Event6","party",5.0,8,locations.get(2)));
        events.add(new Event("Event7","charity",7.0,9,locations.get(2)));
        events.add(new Event("Event8","party",2.6,10,locations.get(3)));
        events.add(new Event("Event9","charity",8.8,11,locations.get(1)));
        events.add(new Event("Event10","meeting",2.1,3,locations.get(4)));

        eventsBooked=new ArrayList<>();

        users = new ArrayList<>();
        users.add(new User("elena.atanasoska", "ea", "Elena", "Atanasoska"));
        users.add(new User("darko.sasanski", "ds", "Darko", "Sasanski"));
        users.add(new User("ana.todorovska", "at", "Ana", "Todorovska"));


    }
}
