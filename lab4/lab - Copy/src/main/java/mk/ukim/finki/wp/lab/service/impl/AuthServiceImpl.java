package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.lab.model.exceptions.UserAlreadyExists;

import mk.ukim.finki.wp.lab.repository.jpa.NewUserRepository;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    // Inject the InMemoryUserRepository
    private final NewUserRepository newUserRepository;

    public AuthServiceImpl(NewUserRepository newUserRepository) {
        this.newUserRepository = newUserRepository;
    }

    @Override
    public User login(String username, String password) {
        // Check if the username and password are not null or empty
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return newUserRepository.findByUsernameAndPassword(username, password).orElseThrow(RuntimeException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        // Check if the username, password, name and surname are not null or empty
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || repeatPassword == null || repeatPassword.isEmpty() || name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        // Check if the password and the repeated password are the same
        if (!password.equals(repeatPassword)) {
            throw new RuntimeException();
        }
        if(newUserRepository.findByUsername(username).isPresent())
        {
            throw new UserAlreadyExists();
        }
        return newUserRepository.save(new User(username, password, name, surname));
    }
}
