package app.controller;

import app.entity.User;
import app.exception.WrongActionException;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/landing")
public class LandingController {
  private final UserService USER_SERVICE;

  public LandingController(UserService USER_SERVICE) {
    this.USER_SERVICE = USER_SERVICE;
  }

  /**
   * http://localhost:8080/landing
   */
  @GetMapping
  public String handle_landing_get(Model model) {
    log.info("GET -> /landing");

    // TODO user id from session
    final long user_id = 1;

    Optional<User> user = USER_SERVICE.findUserById(user_id);
    if (user.isPresent()) {
      log.info("Getting user details from db; table -> users");
      model.addAttribute("full_name", user.get().getFull_name());
      return "landing";
    } else throw new WrongActionException();
  }

  @PostMapping
  public String handle_landing_post(@RequestParam(value = "logout", required = false) String logout,
                                    @RequestParam(value = "get_started", required = false) String getStarted) {
    if ("logout".equals(logout)) {
      // TODO make logout here
      log.info("Logout; Redirect -> /login");
      return "redirect:/login";
    } else if ("get_started".equals(getStarted)) {
      log.info("Redirect -> /main-page-authorized-new");
      return "redirect:/authorized";
    } else throw new WrongActionException();
  }
}