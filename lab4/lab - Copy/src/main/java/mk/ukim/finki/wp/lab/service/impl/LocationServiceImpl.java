package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    public final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
