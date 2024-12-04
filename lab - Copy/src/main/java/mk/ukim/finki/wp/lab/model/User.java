package mk.ukim.finki.wp.lab.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    private String username;

    private String password;
    private String name;
    private String surname;

    public User() {

    }
}
