package app.controller;

import app.entity.User;
import app.exception.DuplicateEmailAddressException;
import app.exception.PasswordDoesntMatchException;
import app.form.FormReg;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/register")
public class RegisterController {
  private final UserService userService;

  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String handle_register_get(Model model) {
    model.addAttribute("user", new User());
    log.info("GET -> /register");
    return "registration";
  }

  @PostMapping
  public String handle_register_post(FormReg formReg) {
    /*
     * username(e-mail address) should be passed as a parameter to findUserByUserName() method
     */
    Optional<User> userByEmail = userService.findUserByEmail(formReg.getEmail());

    if (userByEmail.isPresent()) {
      log.warn("DuplicateEmailAddressException while registering!");
      throw new DuplicateEmailAddressException();
    } else if (!formReg.getPassword().equals(formReg.getConfirm())) {
      log.warn("Password and confirmation password are not the same!");
      throw new PasswordDoesntMatchException();
    } else {
      userService.registerUser(formReg.getFull_name(), formReg.getEmail(), formReg.getPassword());
      log.info("Registration finished successfully, redirecting to login page!");
      return "redirect:/login";
    }
  }
}