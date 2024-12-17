package mk.ukim.finki.wp.lab.model.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LocationException extends RuntimeException{
    public LocationException(Long id) {
        super(String.format("Location with id %d does not exist.", id));
    }
}
