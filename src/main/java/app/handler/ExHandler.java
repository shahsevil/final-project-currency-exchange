package app.handler;

import app.exception.EmailNotUniqueException;
import app.exception.PasswordDoesntMatchException;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@ControllerAdvice
public class ExHandler {

    @ExceptionHandler(EmailNotUniqueException.class)
    public RedirectView handleEmailNotUnique(Model model){
        model.addAttribute("error", "emailnotunique");
        log.warn("This email already exists");
        return new RedirectView("/register");
    }

    @ExceptionHandler(PasswordDoesntMatchException.class)
    public RedirectView handleNoMatch(Model model){
        model.addAttribute("error", "passnomatch");
        log.warn("Password and confirm password does not match");
        return new RedirectView("/register");
    }
}
