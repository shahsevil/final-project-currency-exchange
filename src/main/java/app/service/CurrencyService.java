package app.service;

import app.entity.Currency;
import app.exception.CurrencyNotFoundException;
import app.repo.CurrencyRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CurrencyService {
  private final CurrencyRepo CURRENCY_REPO;

  public CurrencyService(CurrencyRepo currency_repo) {
    CURRENCY_REPO = currency_repo;
  }


  public List<Currency> getAllCurrencies() {
    return CURRENCY_REPO.findAll();
  }

  public long getCurrId(String fromCurrency) {
    Optional<Currency> byAbvName = CURRENCY_REPO.findByAbvName(fromCurrency);
    if (byAbvName.isPresent()) return byAbvName.get().getId();
    else throw new CurrencyNotFoundException();
  }
}