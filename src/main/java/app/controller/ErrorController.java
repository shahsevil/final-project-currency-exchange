package app.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Log4j2
@Controller
public class ErrorController {

  public String handle_error_404(Model model) {
    log.warn("error 404 raised!");
    return "/error";
  }
}