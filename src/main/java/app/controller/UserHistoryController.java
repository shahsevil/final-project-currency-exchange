package app.controller;

import app.entity.User;
import app.entity.UserHistory;
import app.exception.HistoryNotFoundException;
import app.exception.WrongActionException;
import app.service.HistoryService;
import app.service.UserHistoryService;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/user-history")
public class UserHistoryController {
  public final UserHistoryService USER_HISTORY_SERVICE;
  public final UserService USER_SERVICE;
  public final HistoryService HISTORY_SERVICE;

  public UserHistoryController(UserHistoryService USER_HISTORY_RELATION_SERVICE, UserService USER_SERVICE, HistoryService HISTORY_SERVICE) {
    this.USER_HISTORY_SERVICE = USER_HISTORY_RELATION_SERVICE;
    this.USER_SERVICE = USER_SERVICE;
    this.HISTORY_SERVICE = HISTORY_SERVICE;
  }

  /**
   * http://localhost:8080/user-history
   */
  @GetMapping
  public String handle_user_history_get(@RequestParam(value = "go_back", required = false) String btnGoBack,
                                        Model model) {
    log.info("GET -> /user-history");

    if ("go_back".equals(btnGoBack)) {
      // TODO make logout here
      log.info("Redirect -> /authorized");
      return "redirect:/landing";
    }

    final long user_id = 1;

    Optional<User> user = USER_SERVICE.findUserById(user_id);
    if (user.isPresent()) {
      List<UserHistory> histories = USER_HISTORY_SERVICE.getHistories(user_id);
      if (histories.size() > 0) {
        log.info(histories);
        model.addAttribute("histories", histories);
        return "user-history";
      } else throw new HistoryNotFoundException();
    } else throw new WrongActionException();
  }

}