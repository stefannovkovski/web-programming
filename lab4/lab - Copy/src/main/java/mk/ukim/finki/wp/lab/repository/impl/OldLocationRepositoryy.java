package mk.ukim.finki.wp.lab.repository.impl;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OldLocationRepositoryy {
    public List<Location> findAll(){
        return DataHolder.locations;
    }

    public Optional<Location> getById(Long id){
        return DataHolder.locations.stream().filter(i->i.getId().equals(id)).findFirst();
    }


}
