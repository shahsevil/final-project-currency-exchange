package app.controller;

import app.api.currency_exchange.exchange_rates_api_io.eur_to_usd.EurToUsdResponse;
import app.entity.Currency;
import app.service.CurrencyAPIService;
import app.service.CurrencyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/")
public class ExchangeController {
  private final CurrencyAPIService CURRENCY_API_SERVICE;
  private final CurrencyService CURRENCY_SERVICE;

  public ExchangeController(CurrencyAPIService currency_api_service, CurrencyService currency_service) {
    CURRENCY_API_SERVICE = currency_api_service;
    CURRENCY_SERVICE = currency_service;
  }

  /**
   * http://localhost:8080/
   */
  @GetMapping
  public String handle_exchanges_get(Model model) {
    log.info("GET -> /");

    List<Currency> currencies = CURRENCY_SERVICE.getAllCurrencies();
    model.addAttribute("currencies", currencies);
    Optional<EurToUsdResponse> eurToUsd = CURRENCY_API_SERVICE.getLatestEurUSD();

    if (eurToUsd.isPresent()) {
      log.info("date is " + eurToUsd.get().date);
      log.info("base is " + eurToUsd.get().base);
      log.info("rates are " + eurToUsd.get().rates);
      log.info(eurToUsd.get().rates.USD);

      final double rate = eurToUsd.get().rates.USD;

      log.info("rate is : " + rate);

      model.addAttribute("exchange_result", rate);
      model.addAttribute("from_to", String.format("1USD/%sEUR", 1 / rate));
      model.addAttribute("to_from", String.format("1EUR/%sUSD", rate));
    }

    return "main-page-new";
  }

  @PostMapping
  public String handle_post_exchange() {
    throw new IllegalArgumentException("Should be implemented...");
  }
}