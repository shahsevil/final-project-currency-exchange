package app.service;

import app.entity.Currency;
import app.repo.CurrencyRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

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
}