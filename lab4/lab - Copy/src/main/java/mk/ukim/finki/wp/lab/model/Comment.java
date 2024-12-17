package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String userName;
    public String comment;

    @ManyToOne
    public Event event;

    public Comment(String userName, String comment,Event event) {
        this.userName = userName;
        this.comment = comment;
        this.event=event;
    }

    public Comment() {

    }
}
