package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String description;
    double popularityScore;
    Integer numTickets;

    @ManyToOne
    private Location location;

    @OneToMany(mappedBy = "event")
    private List<Comment> comments;

    public Event(String name, String description, double popularityScore, Integer numTickets, Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.numTickets = numTickets;
        this.location = location;
        this.comments = new ArrayList<>();
    }

    public Event() {

    }
}
