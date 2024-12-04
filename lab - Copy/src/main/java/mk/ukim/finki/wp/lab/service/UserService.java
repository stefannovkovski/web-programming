package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User saveUser(String nameUser,String comment);
}
