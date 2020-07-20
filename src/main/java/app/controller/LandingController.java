package app.controller;

import app.security.XUserDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/landing")
public class LandingController {

    @GetMapping
    public String handle_landing_get(Model model, Authentication authentication) {
        log.info("GET -> /landing");
        XUserDetails user = (XUserDetails) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        return "landing";
    }
}