package mk.ukim.finki.wp.lab.model.exceptions;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists() {
        super(String.format("User already exists"));
    }
}
