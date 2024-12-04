package mk.ukim.finki.wp.lab.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Comment;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.CommentService;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
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
        model.addAttribute("categories", events);
        model.addAttribute("locations",locations);
        return "listEvents";
    }

    @GetMapping("/add-form")
    public String addEventPage(Model model) {
        List<Location> locations = this.locationService.findAll();
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Double popularityScore,
                              @RequestParam Integer numberOftickets,
                              @RequestParam Long locationId) {
        this.eventService.saveEvent(name, description, popularityScore, numberOftickets, locationId);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
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
    public String deleteEvent(@PathVariable Long id, Model model){
        this.eventService.deleteById(id);
        return "redirect:/events";
    }

    @PostMapping("/comment")
    public String comment(HttpServletRequest request,@RequestParam Long id, @RequestParam String comment) {
        User user = (User) request.getSession().getAttribute("user");
        Event event = eventService.findById(id).get();

        Comment newComment = new Comment(user.getUsername(), comment,event);
        this.commentService.saveComment(newComment);

        return "redirect:/events";
    }

}
