package app.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String handle_login_get() {
        log.info("GET -> /login");
        return "login";
    }

    @GetMapping("/login-error")
    public String errorLoginHandler(Model model) {
        model.addAttribute("error", true);
        log.info("Incorrect password or login");
        return "redirect:/error";
    }
}