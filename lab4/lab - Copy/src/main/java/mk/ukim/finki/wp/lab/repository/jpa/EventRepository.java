package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findAllByLocation_id(Long locationId);

    List<Event> searchAllByNameOrDescriptionContaining(String name, String description);

    void deleteByName(String name);

    Optional<Event> findByNameAndDescription(String name, String description);
}
