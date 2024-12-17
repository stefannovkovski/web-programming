package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    public final EventService eventService;
    public final EventBookingService eventBookingService;


    public EventBookingController(EventService eventService, EventBookingService eventBookingService) {
        this.eventService = eventService;
        this.eventBookingService = eventBookingService;
    }

    @PostMapping
    public String showBooking(@RequestParam String category,
                              @RequestParam Integer numTickets,
                              @RequestParam String personName,
                              @RequestParam Integer numTicketsCategory, Model model, HttpServletRequest req){


        if (numTicketsCategory-numTickets<0)
        {
            model.addAttribute("error",true);
        }
        else {
            EventBooking booked = eventBookingService.placeBooking(category,personName,req.getRemoteAddr(),numTickets);
            model.addAttribute("error",false);
            model.addAttribute("booking",booked);
        }
        return "bookingConfirmation";
    }
}
