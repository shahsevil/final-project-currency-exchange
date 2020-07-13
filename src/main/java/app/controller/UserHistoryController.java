package app.controller;

import app.entity.History;
import app.entity.User;
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

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
  public String handle_user_history_get(Model model) {
    log.info("GET -> /user-history");

    final long user_id = 1;

    Optional<User> user = USER_SERVICE.findUserById(user_id);
    if (user.isPresent()) {

      List<Long> historyIds = USER_HISTORY_SERVICE.getHistoryIds(user_id);
      if (historyIds.size() > 0) {
        // TODO find histories
        return "user-history";
      } else throw new HistoryNotFoundException();
    } else throw new WrongActionException();
  }

}