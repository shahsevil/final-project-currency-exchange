package app.controller;

import app.exception.WrongActionException;
import app.security.XUserDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping
    public String handle_landing_post(@RequestParam(value = "get_started", required = false) String btn){
        if ("get_started".equals(btn)) {
            log.info("Redirect -> /authorized");
            return "redirect:/authorized";
        }
        throw new WrongActionException();
    }

}