package app.controller;

import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/landing")
public class LandingController {
  private final UserService userService;

  public LandingController(UserService userService) {
    this.userService = userService;
  }

  /**
   * http://localhost:8080/landing
   */
  @GetMapping
  public String handle_landing_get() {
    log.info("GET -> /landing");
    return "landing";
  }

  @PostMapping
  public String handle_landing_post() {
    throw new IllegalArgumentException("Should be implemented, session must end here");
  }
}