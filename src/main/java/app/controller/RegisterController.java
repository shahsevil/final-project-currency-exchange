package app.controller;

import app.entity.User;
import app.dto.FormReg;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/registration")
public class RegisterController {
    private final UserService USER_SERVICE;

    public RegisterController(UserService USER_SERVICE) {
        this.USER_SERVICE = USER_SERVICE;
    }

    @GetMapping
    public String handle_register_get(Model model) {
        model.addAttribute("user", new User());
        log.info("GET -> /registration");
        return "registration";
    }

    @PostMapping
    public String handle_register_post(FormReg formReg) {
        log.info("POST -> /registration");
        USER_SERVICE.registerUser(formReg);
        return "redirect:/login";
    }

}