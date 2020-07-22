//package app.handler;
//
//import app.exception.*;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.servlet.view.RedirectView;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//import java.net.http.HttpResponse;
//
//@Log4j2
//@Controller
//@ControllerAdvice
//public class ExHandler implements ErrorController {
//
//  @ExceptionHandler(UserNotFoundException.class)
//  public ResponseEntity<?> handleUserNotFound(Model model) {
//    model.addAttribute("error", "User not found");
//    log.warn("User not found");
////    return new RedirectView("./error");
//    return new ResponseEntity<>("error-404", HttpStatus.NOT_FOUND);
//  }
//
//  @ExceptionHandler(DuplicateEmailAddressException.class)
//  public RedirectView handleEmailNotUnique(Model model) {
//    model.addAttribute("error", "Email address is duplicate!");
//    log.warn("This email already exists");
//    return new RedirectView("/registration");
//  }
//
//  @ExceptionHandler(PasswordDoesntMatchException.class)
//  public RedirectView handleNoMatch(Model model) {
//    model.addAttribute("error", "Password and confirm password are not the same!");
//    log.warn("Password and confirm password does not match");
//    return new RedirectView("/registration");
//  }
//
//  @ExceptionHandler(RateNotFoundException.class)
//  public RedirectView handleNoRate(Model model) {
//    model.addAttribute("error", "rate_not_found");
//    log.warn("Rate not found");
//    return new RedirectView("./error");
//  }
//
//  @ExceptionHandler(WrongActionException.class)
//  public RedirectView handleWrongAction(Model model) {
//    model.addAttribute("error", "wrong operation");
//    log.warn("Smth went wrong");
//    return new RedirectView("./error");
//  }
//
//  @ExceptionHandler(HistoryNotFoundException.class)
//  public ResponseEntity<?> handleNoHistory(Model model) {
//    model.addAttribute("error", "not found any histories");
//    log.warn("Histories not found!");
////    return new RedirectView("./error");
//    return new ResponseEntity<>("error-404", HttpStatus.NOT_FOUND);
//  }
//
//
////  @GetMapping("/error")
////  @ExceptionHandler(value = {CurrencyNotFoundException.class,
////          DuplicateEmailAddressException.class,
////          HistoryNotFoundException.class,
////          InvalidTokenEx.class,
////          PasswordDoesntMatchException.class,
////          RateNotFoundException.class,
////          ResetEmptyEx.class,
////          UserNotFoundException.class,
////          WrongActionException.class,
////          RuntimeException.class
////  })
////  public String handleError(HttpServletRequest rq) {
////    Object status = rq.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
////    if (status != null) {
////      int code = Integer.parseInt(status.toString());
////      if (code == HttpStatus.NOT_FOUND.value()) {
////        log.error("ERROR -> 404");
////        return "error-404";
////      }
////      else if (code == HttpStatus.FORBIDDEN.value()) {
////        log.error("ERROR -> 403");
////        return "error-403";
////      }
////      else if (code == HttpStatus.INTERNAL_SERVER_ERROR.value()){
////        log.error("ERROR -> 500");
////        return "error-500";
////      }
////      else if (code == HttpStatus.FOUND.value()){
////        log.warn("ERROR -> 302");
////        return "error-302";
////      }
////        log.error("ERROR -> "+ status);
////    }
////    log.error("ERROR -> exit -> "+ status);
////    return "errors";
////  }
////
//  @Override
//  public String getErrorPath() {
//    return "/error";
//  }
//}
