package app.controller;

import app.entity.User;
import app.exception.DuplicateEmailAddressException;
import app.exception.PasswordDoesntMatchException;
import app.exception.WrongActionException;
import app.form.FormReg;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/registration")
public class RegisterController {
  private final UserService USER_SERVICE;

  public RegisterController(UserService USER_SERVICE) {
    this.USER_SERVICE = USER_SERVICE;
  }

  @GetMapping
  public String handle_register_get(Model model) {
    model.addAttribute("user", new User());
    log.info("GET -> /registration");
    return "registration";
  }

  @PostMapping
  public String handle_register_post(FormReg formReg) {
    log.info(formReg.getPassword());
    log.info(formReg.getConfirmPassword());

    if ("register".equals(formReg.getBtnRegister())) {
      Optional<User> userByEmail = USER_SERVICE.findUserByEmail(formReg.getEmail());

      if (userByEmail.isPresent()) {
        log.warn("DuplicateEmailAddressException while registering!");
        throw new DuplicateEmailAddressException();
      } else if (!formReg.getPassword().equals(formReg.getConfirmPassword())) {
        log.warn("Password and confirmation password are not the same!");
        throw new PasswordDoesntMatchException();
      } else {
        USER_SERVICE.registerUser(formReg.getFull_name(), formReg.getEmail(), formReg.getPassword());
        log.info("Registration finished successfully, redirecting to login page!");
        return "redirect:/login";
      }
    } else throw new WrongActionException();
  }
}