package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class EventListServlet extends HttpServlet {

    public final EventService eventService;
    public final SpringTemplateEngine springTemplateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        List<Event> events=null;

        String searchTerm = req.getParameter("searchTerm");
        String rating = req.getParameter("rating");

        if (searchTerm != null &&  !searchTerm.isEmpty() && rating!=null && !rating.isEmpty()) {
            events = eventService.searchEvents(searchTerm).stream()
                    .filter(r -> r.getPopularityScore() > Double.parseDouble(rating)).toList();
        } else if (searchTerm != null &&  !searchTerm.isEmpty() ) {
            events = eventService.searchEvents(searchTerm);
        }
        else if (rating!=null && !rating.isEmpty()) {
                events = eventService.listAll().stream().filter(r -> r.getPopularityScore() > Double.parseDouble(rating)).toList();
        } else {
            events = eventService.listAll();
        }
        WebContext context = new WebContext(webExchange);
        context.setVariable("categories", events);

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }

}
