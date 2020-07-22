package app.controller;

import app.api.currency_exchange.exchange_rates_api_io.eur_to_usd.EurToUsdResponse;
import app.api.currency_exchange.exchange_rates_api_io.latest_with_base.LatestBaseResponse;
import app.entity.Currency;
import app.exception.RateNotFoundException;
import app.exception.ServiceUnavailable;
import app.service.CurrencyAPIService;
import app.service.CurrencyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static app.utils.MathUtils.round_till;
import static app.utils.Util.getOrDefault;

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
      final double rate = eurToUsd.get().rates.USD;

      log.info("date is " + eurToUsd.get().date);
      log.info("base is " + eurToUsd.get().base);
      log.info("rates are " + eurToUsd.get().rates);
      log.info("rate is : " + rate);
      log.info(eurToUsd.get().rates.USD);

      model.addAttribute("default_from_curr", "Please select a currency");
      model.addAttribute("default_to_curr", "Please select a currency");
      model.addAttribute("entered_value", 0);
      model.addAttribute("exchange_result", 0);
      model.addAttribute("from_to", String.format("1USD/%sEUR", round_till(1 / rate, 10000)));
      model.addAttribute("to_from", String.format("1EUR/%sUSD", round_till(rate, 10000)));
      return "main-page-new";
    } else {
      log.warn("There is a problem while getting latest rates!");
      throw new ServiceUnavailable();
    }
  }

  @PostMapping
  public String handle_post_exchange(@RequestParam(value = "login", required = false) String btnLogin,
                                     @RequestParam(value = "fromCurr", required = false) String fromCurr,
                                     @RequestParam(value = "fromValue", required = false) Double fromValue,
                                     @RequestParam(value = "toCurr", required = false) String toCurr,
                                     @RequestParam(value = "exchange", required = false) String btnExchange,
                                     Model model) {
    log.info("============");
    log.info("btnLogin is " + btnLogin + " btnExchange is " + btnExchange);
    log.info("fromCurr is " + fromCurr + " toCurr is " + toCurr + " fromValue is " + fromValue);
    log.info("============");

    if ("login".equals(btnLogin)) {
      log.info("Redirect -> login");
      return "redirect:/login";
    }

    log.info("Calculate exchange -> main-page");
    log.info("fromCurrency is " + fromCurr);
    log.info("toCurrency is " + toCurr);
    log.info("enteredValue is " + fromValue);

    String fromCurrency = getOrDefault(fromCurr, "EUR");
    double enteredValue = getOrDefault(fromValue, 1D);
    String toCurrency = getOrDefault(toCurr, "USD");

    log.info("fromCurrency is " + fromCurrency);
    log.info("toCurrency is " + toCurrency);
    log.info("enteredValue is " + enteredValue);

    Optional<LatestBaseResponse> rates = CURRENCY_API_SERVICE.getLatestRatesWithBase(fromCurrency);

    if (rates.isPresent()) {
      double rate;
      if ("EUR".equals(fromCurrency) && "EUR".equals(toCurrency)) rate = 1d;
      else rate = rates.get().rates.getAllCurrencyNamesAndValues()
              .entrySet().stream()
              .filter(e -> e.getKey().equals(toCurrency))
              .map(Map.Entry::getValue)
              .findFirst()
              .orElse(1d);

      List<Currency> currencies = CURRENCY_SERVICE.getAllCurrencies();

      model.addAttribute("default_from_curr", fromCurrency);
      model.addAttribute("default_to_curr", toCurrency);
      model.addAttribute("currencies", currencies);
      model.addAttribute("entered_value", enteredValue);
      model.addAttribute("exchange_result", round_till(enteredValue * rate, 1000));
      model.addAttribute("from_to", String.format("1%s/%s%s", toCurrency, round_till(1 / rate, 1000), fromCurrency));
      model.addAttribute("to_from", String.format("1%s/%s%s", fromCurrency, round_till(rate, 1000), toCurrency));
      return "main-page-new";
    } else throw new RateNotFoundException();
  }
}