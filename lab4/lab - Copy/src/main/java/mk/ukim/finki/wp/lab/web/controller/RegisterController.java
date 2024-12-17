package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.exceptions.UserAlreadyExists;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping
    public String getRegisterPage(){
        return "register";
    }

    @PostMapping
    public String register(HttpServletRequest request, Model model){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        try{
            this.authService.register(username,password,password,name,surname);
            return "redirect:/login";
        }catch (RuntimeException ex){
            model.addAttribute("hasError",true);
            model.addAttribute("error",ex.getMessage());
            return "register";
        }
    }

}
