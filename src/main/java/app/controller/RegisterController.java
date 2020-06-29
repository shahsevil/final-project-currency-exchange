package app.controller;

import app.entity.User;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String handle_register_get(Model model) {
        model.addAttribute("user", new User());
        log.info("GET -> /register");
        return "register";
    }

    @PostMapping
    public String handle_register_post(@ModelAttribute("user") User user) {

        /**
         * username should be passed as a parameter to findUserByUserName() method
         */
//        Optional<User> userByUserName = userService.findUserByUserName();
//
//        if (userByUserName.isPresent()) {
//            log.warn("This username already exists");
//            return "redirect:/register";
//        }

        userService.registerUser(user);
        return "redirect:/login";
    }
}
