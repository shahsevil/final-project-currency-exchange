package app.service;

import app.entity.UserHistory;
import app.repo.UserHistoryRelationRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class UserHistoryService {
  public final UserHistoryRelationRepo U_H_REL_REPO;

  public UserHistoryService(UserHistoryRelationRepo U_H_REL_REPO) {
    this.U_H_REL_REPO = U_H_REL_REPO;
  }

  public List<UserHistory> getHistories(long user_id) {
    List<UserHistory> allHistories = U_H_REL_REPO.findAllHistories(user_id);
    log.info(allHistories);
    return allHistories;
  }
}
