package app.handler;

import app.exception.DuplicateEmailAddressException;
import app.exception.PasswordDoesntMatchException;
import app.exception.RateNotFoundException;
import app.exception.WrongActionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@ControllerAdvice
public class ExHandler {

  @ExceptionHandler(DuplicateEmailAddressException.class)
  public RedirectView handleEmailNotUnique(Model model) {
    model.addAttribute("error", "emailnotunique");
    log.warn("This email already exists");
    return new RedirectView("/registration.html");
  }

  @ExceptionHandler(PasswordDoesntMatchException.class)
  public RedirectView handleNoMatch(Model model) {
    model.addAttribute("error", "passnomatch");
    log.warn("Password and confirm password does not match");
    return new RedirectView("/registration.html");
  }

  @ExceptionHandler(RateNotFoundException.class)
  public RedirectView handleNoRate(Model model) {
    model.addAttribute("error", "rate_not_found");
    log.warn("Rate not found");
    return new RedirectView("./main-page-new");
  }

  @ExceptionHandler(WrongActionException.class)
  public RedirectView handleWrongAction(Model model) {
    model.addAttribute("error", "wrong operation");
    log.warn("Smth went wrong");
    return new RedirectView("./main-page-authorized-new");
  }


}
