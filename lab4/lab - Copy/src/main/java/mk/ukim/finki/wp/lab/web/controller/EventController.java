package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Comment;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.CommentService;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    public final EventService eventService;
    public final LocationService locationService;
    public final CommentService commentService;

    public EventController(EventService eventService, LocationService locationService, CommentService commentService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.commentService = commentService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String searchTerm,
                                @RequestParam(required = false) String rating,
                                @RequestParam(required = false) String error,
                                @RequestParam(required = false) Long locationId,
                                Model model){
        List<Event> events = eventService.listAll();
        List<Location> locations = locationService.findAll();
        if (locationId != null) {
            events =eventService.findByLocation(locationId);
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            events = eventService.searchEvents(searchTerm);
        }
        if (rating != null && !rating.isEmpty()) {
            double ratingValue = Double.parseDouble(rating);
            events = events.stream()
                    .filter(event -> event.getPopularityScore() > ratingValue)
                    .toList();
        }
        if (error!=null){
            model.addAttribute("error", error);

        }
        model.addAttribute("categories", events);
        model.addAttribute("locations",locations);
        return "listEvents";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String addEventPage(Model model) {
        List<Location> locations = this.locationService.findAll();
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveEvent(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double popularityScore,
            @RequestParam Integer numberOftickets,
            @RequestParam Long locationId,
            Model model) {

        try {
            if (id != null) {
                eventService.updateEvent(id, name, description, popularityScore, numberOftickets, locationId);
            } else {
                eventService.saveEvent(name, description, popularityScore, numberOftickets, locationId);
            }
            return "redirect:/events";
        } catch (RuntimeException ex) {
            return "redirect:/events?error=" + ex.getMessage();
        }
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editEvent(@PathVariable Long id, Model model) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Location> loc = this.locationService.findAll();
            model.addAttribute("locations", loc);
            model.addAttribute("event", event);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }


    @GetMapping("/comment-form/{id}")
    public String commentEvent(@PathVariable Long id, Model model) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Location> loc = this.locationService.findAll();
            model.addAttribute("locations", loc);
            model.addAttribute("event", event);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEvent(@PathVariable Long id, Model model){
        this.eventService.deleteById(id);
        return "redirect:/events";
    }

    @PostMapping("/comment")
    public String comment(HttpServletRequest request,@RequestParam Long id, @RequestParam String comment) {
        String username = request.getRemoteUser();
        Event event = eventService.findById(id).get();
        Comment newComment = new Comment(username, comment,event);
        this.commentService.saveComment(newComment);

        return "redirect:/events";
    }

}
