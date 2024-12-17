//package mk.ukim.finki.wp.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Event;
//import mk.ukim.finki.wp.lab.model.EventBooking;
//import mk.ukim.finki.wp.lab.service.EventBookingService;
//import mk.ukim.finki.wp.lab.service.EventService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Stream;
//
//
//@WebServlet(name = "EventBookingServlet",urlPatterns = "/eventBooking")
//public class EventBookingServlet extends HttpServlet {
//    public final EventService eventService;
//
//    public final EventBookingService eventBookingService;
//    public final SpringTemplateEngine springTemplateEngine;
//
//    public EventBookingServlet(EventService eventService, EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine) {
//        this.eventService = eventService;
//        this.eventBookingService = eventBookingService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//
//        String category = req.getParameter("category");
//        String number = req.getParameter("numTickets");
//        String name = req.getParameter("personName");
//        String realNumberofTickets = req.getParameter("numTicketsCategory");
//
//        if (Integer.parseInt(realNumberofTickets)-Integer.parseInt(number)<0)
//        {
//            context.setVariable("error",true);
//        }
//        else {
//            EventBooking booked = eventBookingService.placeBooking(category,name,req.getRemoteAddr(),Integer.parseInt(number));
//            context.setVariable("error",false);
//            context.setVariable("booking",booked);
//        }
//        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
//    }
//}
