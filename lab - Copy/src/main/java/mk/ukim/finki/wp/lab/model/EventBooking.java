package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class EventBooking {

    @Id
    String eventName;

    String attendeeName;
    String attendeeAddress;
    Long numberOfTickets;

    public EventBooking() {

    }
}
