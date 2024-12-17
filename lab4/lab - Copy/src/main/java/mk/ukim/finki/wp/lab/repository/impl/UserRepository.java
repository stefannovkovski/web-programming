package mk.ukim.finki.wp.lab.repository.impl;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    public List<User> findAll(){
        return DataHolder.users;
    }
    public User save(User user){
        DataHolder.users.add(user);
        return user;
    }
}
