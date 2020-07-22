package app.handler;

import app.exception.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@ControllerAdvice
public class AppExHandler {

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFoundException.class)
  public ModelAndView handleUserNotFound(HttpServletRequest rq, Exception ex) {
    log.error("User not found exception handled -> " + ex);
    ModelAndView mav = new ModelAndView();
    mav.addObject("error", "user not found!");
    mav.addObject("url", rq.getRequestURL());
    mav.addObject("ex", ex);
    mav.addObject("error_reason", "User not found in the db");
    mav.addObject("go_url", "/landing");
    mav.setViewName("error-404");
    return mav;
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(RateNotFoundException.class)
  public ModelAndView handleRateNotFound(HttpServletRequest rq, Exception ex) {
    log.error("rate not found exception handled -> " + ex);
    ModelAndView mav = new ModelAndView();
    mav.addObject("error", "rate not found!");
    mav.addObject("url", rq.getRequestURL());
    mav.addObject("ex", ex);
    mav.addObject("error_reason", "Requested rate not found");
    mav.addObject("go_url", "/login");
    mav.setViewName("error-404");
    return mav;
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(HistoryNotFoundException.class)
  public ModelAndView handleHistoryNotFound(HttpServletRequest rq, Exception ex) {
    log.error("history not found exception handled -> " + ex);
    ModelAndView mav = new ModelAndView();
    mav.addObject("error", "history not found!");
    mav.addObject("url", rq.getRequestURL());
    mav.addObject("ex", ex);
    mav.addObject("error_reason", "No any histories found");
    mav.addObject("go_url", "/authorized");
    mav.setViewName("error-404");
    return mav;
  }

  @ResponseStatus(value = HttpStatus.CONFLICT)
  @ExceptionHandler(DuplicateEmailAddressException.class)
  public ModelAndView handleEmailNotUnique(HttpServletRequest rq, Exception ex) {
    log.warn("This email already exists");
    ModelAndView mav = new ModelAndView();
    mav.addObject("error", "email already taken");
    mav.addObject("url", rq.getRequestURL());
    mav.addObject("ex", ex);
    mav.addObject("error_reason", "This email has already exists");
    mav.addObject("go_url", "/login");
    mav.setViewName("error-409");
    return mav;
  }

  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(PasswordDoesntMatchException.class)
  public ModelAndView handlePasswordNoMatch(HttpServletRequest rq, Exception ex) {
    log.warn("Password and confirm password does not match");
    ModelAndView mav = new ModelAndView();
    mav.addObject("error", "password and confirm password does not match");
    mav.addObject("url", rq.getRequestURL());
    mav.addObject("ex", ex);
    mav.addObject("error_reason", "Password and confirm password does not match");
    mav.addObject("go_url", "/registration");
    mav.setViewName("error-401");
    return mav;
  }

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(InternalServerError.class)
  public ModelAndView handleInternalServerError(HttpServletRequest rq, Exception ex) {
    log.warn("Something went wrong, INTERNAL SERVER ERROR");
    ModelAndView mav = new ModelAndView();
    mav.addObject("error", "INTERNAL SERVER ERROR");
    mav.addObject("url", rq.getRequestURL());
    mav.addObject("ex", ex);
    mav.addObject("exception_name", ex.getClass().getSimpleName());
    mav.setViewName("error-500");
    return mav;
  }

  @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
  @ExceptionHandler(ServiceUnavailable.class)
  public ModelAndView handleNoServiceError(HttpServletRequest rq, Exception ex) {
    log.warn("Something went wrong");
    ModelAndView mav = new ModelAndView();
    mav.addObject("error", "Service unavailable!");
    mav.addObject("url", rq.getRequestURL());
    mav.addObject("ex", ex);
    mav.addObject("exception_name", ex.getClass().getSimpleName());
    mav.setViewName("error-503");
    return mav;
  }
}