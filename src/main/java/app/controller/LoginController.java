package app.controller;

import app.entity.User;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * http://localhost:8080/login
     */
    @GetMapping()
    public String handle_login_get() {
        log.info("GET -> /login");
        return "login";
    }

    @PostMapping
    public String handle_post_login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            HttpServletRequest httpServletRequest) {

        Optional<User> byUserNameAndPassword = userService.findByUserNameAndPassword(username, password);

        if (!byUserNameAndPassword.isPresent()) {
            log.warn("POST -> login error");
            return "login";
        }

        HttpSession session = httpServletRequest.getSession();
        log.info("Session -> ", session);
        log.info("Redirecting -> /landing");
        return "redirect:/landing";
    }
}