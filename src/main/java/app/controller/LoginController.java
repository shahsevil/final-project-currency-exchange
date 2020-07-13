package app.controller;

import app.entity.User;
import app.exception.UserNotFoundException;
import app.exception.WrongActionException;
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

@Log4j2
@Controller
@RequestMapping("/login")
public class LoginController {
  private final UserService USER_SERVICE;

  public LoginController(UserService USER_SERVICE) {
    this.USER_SERVICE = USER_SERVICE;
  }

  /**
   * http://localhost:8080/login
   */
  @GetMapping
  public String handle_login_get() {
    log.info("GET -> /login");
    return "login";
  }

  @PostMapping
  public String handle_post_login(@RequestParam(value = "username", required = false) String username,
                                  @RequestParam(value = "password", required = false) String password,
                                  @RequestParam(value = "login", required = false) String btnLogin,
                                  HttpServletRequest httpServletRequest) {

    if ("login".equals(btnLogin)) {
      Optional<User> byUserNameAndPassword = USER_SERVICE.findUserByEmailAndPassword(username, password);
      if (byUserNameAndPassword.isPresent()) {
        log.info("Login successfully completed!");
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user_id", byUserNameAndPassword.get().getId());
        log.info("Session ->" + session);
        log.info("POST -> /landing");
        return "redirect:landing";
      } else throw new UserNotFoundException();
    } else throw new WrongActionException();
  }
}