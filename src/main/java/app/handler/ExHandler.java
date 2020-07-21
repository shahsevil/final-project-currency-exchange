package app.handler;

import app.exception.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@ControllerAdvice
public class ExHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public RedirectView handleUserNotFound(Model model) {
    model.addAttribute("error", "User not found");
    log.warn("User not found");
    return new RedirectView("./error");
  }

  @ExceptionHandler(DuplicateEmailAddressException.class)
  public RedirectView handleEmailNotUnique(Model model) {
    model.addAttribute("error", "Email address is duplicate!");
    log.warn("This email already exists");
    return new RedirectView("/registration");
  }

  @ExceptionHandler(PasswordDoesntMatchException.class)
  public RedirectView handleNoMatch(Model model) {
    model.addAttribute("error", "Password and confirm password are not the same!");
    log.warn("Password and confirm password does not match");
    return new RedirectView("/registration");
  }

  @ExceptionHandler(RateNotFoundException.class)
  public RedirectView handleNoRate(Model model) {
    model.addAttribute("error", "rate_not_found");
    log.warn("Rate not found");
    return new RedirectView("./error");
  }

  @ExceptionHandler(WrongActionException.class)
  public RedirectView handleWrongAction(Model model) {
    model.addAttribute("error", "wrong operation");
    log.warn("Smth went wrong");
    return new RedirectView("./error");
  }

  @ExceptionHandler(HistoryNotFoundException.class)
  public RedirectView handleNoHistory(Model model) {
    model.addAttribute("error", "not found any histories");
    log.warn("Histories not found!");
    return new RedirectView("./error");
  }


}
