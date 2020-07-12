package app.service;

import app.entity.Currency;
import app.entity.History;
import app.entity.User;
import app.repo.HistoryRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Log4j2
@Service
public class HistoryService {
  private final HistoryRepo HISTORY_REPO;

  public HistoryService(HistoryRepo history_repo) {
    HISTORY_REPO = history_repo;
  }

  public void addHistory(double amount, LocalDate fromDate,
                         LocalDate toDate, long fromCurrId, long toCurrId,
                         LocalDate operationDate, double rate, long userId) {

    History history = new History(fromCurrId, toCurrId, fromDate, toDate, operationDate, rate, amount,
            Arrays.asList(new Currency(fromCurrId), new Currency(toCurrId)), new User(userId));

    log.info("history is : " + history);

    HISTORY_REPO.save(history);
  }
}
