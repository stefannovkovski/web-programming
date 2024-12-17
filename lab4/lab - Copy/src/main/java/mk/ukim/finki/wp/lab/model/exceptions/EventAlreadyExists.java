package mk.ukim.finki.wp.lab.model.exceptions;


public class EventAlreadyExists extends RuntimeException{
    public EventAlreadyExists() {
        super(String.format("Event with that name and description already exists!"));
    }
}
