package app.controller;

import app.api.currency_exchange.exchange_rates_api_io.eur_to_usd.EurToUsdResponse;
import app.api.currency_exchange.exchange_rates_api_io.range_with_base.HistoricalRates;
import app.entity.Currency;
import app.entity.User;
import app.exception.RateNotFoundException;
import app.exception.UserNotFoundException;
import app.exception.ServiceUnavailable;
import app.security.XUserDetails;
import app.service.CurrencyAPIService;
import app.service.CurrencyService;
import app.service.HistoryService;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static app.utils.MathUtils.round_till;
import static app.utils.Util.getOrDefault;
import static app.utils.Util.getOrDefaultDate;

@Log4j2
@Controller
@RequestMapping("/authorized")
public class ExchangeAuthorizedController {
  private final CurrencyAPIService CURRENCY_API_SERVICE;
  private final CurrencyService CURRENCY_SERVICE;
  private final HistoryService HISTORY_SERVICE;
  private final UserService USER_SERVICE;

  public ExchangeAuthorizedController(CurrencyAPIService currency_api_service, CurrencyService currency_service, HistoryService history_service, UserService USER_SERVICE) {
    CURRENCY_API_SERVICE = currency_api_service;
    CURRENCY_SERVICE = currency_service;
    HISTORY_SERVICE = history_service;
    this.USER_SERVICE = USER_SERVICE;
  }

  /**
   * http://localhost:8080/authorized
   */
  @GetMapping
  public String handle_exchanges_get(Model model, Authentication authentication) {
    log.info("GET -> /authorized");

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

      XUserDetails xUser = (XUserDetails) authentication.getPrincipal();

      User user = USER_SERVICE.findUserById(xUser.getId()).orElseThrow(UserNotFoundException::new);

      model.addAttribute("fullname", user.getFull_name());
      model.addAttribute("default_from_curr", "Please select a currency");
      model.addAttribute("default_to_curr", "Please select a currency");
      model.addAttribute("entered_value", 0);
      model.addAttribute("exchange_result", 0);
      model.addAttribute("from_to", String.format("1USD/%sEUR", round_till(1 / rate, 10000)));
      model.addAttribute("to_from", String.format("1EUR/%sUSD", round_till(rate, 10000)));
      return "main-page-authorized-new";
    } else {
      log.warn("There is a problem while getting latest rates!");
      throw new ServiceUnavailable();
    }
  }

  @PostMapping
  public String handle_post_exchange(@RequestParam(value = "fromCurr", required = false) String fromCurr,
                                     @RequestParam(value = "fromValue", required = false) Double fromValue,
                                     @RequestParam(value = "toCurr", required = false) String toCurr,
                                     @RequestParam(value = "fromDate", required = false) String fDate,
                                     @RequestParam(value = "toDate", required = false) String tDate,
                                     @RequestParam(value = "exchange", required = false) String btnExchange,
                                     @RequestParam(value = "rate_history", required = false) String btnRateHistory,
                                     @RequestParam(value = "user_history", required = false) String btnUserHistory,
                                     Model model,
                                     RedirectAttributes redirectAttributes,
                                     Authentication authentication) {

    log.info("Calculate exchange -> main-page-authorized");

    log.info("fromCurrency is " + fromCurr);
    log.info("toCurrency is " + toCurr);
    log.info("enteredValue is " + fromValue);
    log.info("fromDate is " + fDate);
    log.info("toDate is " + tDate);

    String fromCurrency = getOrDefault(fromCurr, "EUR");
    double enteredValue = getOrDefault(fromValue, 1D);
    String toCurrency = getOrDefault(toCurr, "USD");
    LocalDate fromDate = getOrDefaultDate(fDate, LocalDate.now().minusDays(3));
    LocalDate toDate = getOrDefaultDate(tDate, LocalDate.now());

    log.info("fromCurrency is " + fromCurrency);
    log.info("toCurrency is " + toCurrency);
    log.info("enteredValue is " + enteredValue);
    log.info("fromDate is " + fromDate);
    log.info("toDate is " + toDate);

    if ("exchange".equals(btnExchange)) {
      HistoricalRates usd = CURRENCY_API_SERVICE.getRangeWithBase(fromDate, toDate, fromCurrency)
              .orElseThrow(RateNotFoundException::new);

      log.info("range response is " + usd);
      log.info("range response base is " + usd.base);
      log.info("range response start_date is " + usd.start_at);
      log.info("range response end_date is " + usd.end_at);
      log.info("range response rates are " + usd.rates.toString());

      LocalDate latestDate = usd.rates.keySet().stream().findFirst().orElseThrow(RateNotFoundException::new);

      log.info("range response rate is " + usd.rates.get(latestDate));

      double rate;
      if ("EUR".equals(fromCurrency) && "EUR".equals(toCurrency)) rate = 1d;
      else rate = usd.rates.get(latestDate)
              .getAllCurrencyNamesAndValues()
              .entrySet()
              .stream()
              .filter(e -> e.getKey().equals(toCurrency))
              .map(Map.Entry::getValue)
              .findFirst()
              .orElse(1d);

      long fromCurrId = CURRENCY_SERVICE.getCurrId(fromCurrency);
      long toCurrId = CURRENCY_SERVICE.getCurrId(toCurrency);

      log.info("==== from db ====");
      log.info("rate is : " + rate);
      log.info("fromCurrId is : " + fromCurrId);
      log.info("toCurrId is : " + toCurrId);
      log.info("==== from db ====");

      XUserDetails xUser = (XUserDetails) authentication.getPrincipal();
      HISTORY_SERVICE.addHistory(enteredValue, fromDate, toDate,
              fromCurrId, toCurrId, LocalDate.now(), rate, xUser.getId());

      List<Currency> currencies = CURRENCY_SERVICE.getAllCurrencies();

      model.addAttribute("default_from_curr", fromCurrency);
      model.addAttribute("default_to_curr", toCurrency);
      model.addAttribute("currencies", currencies);
      model.addAttribute("entered_value", enteredValue);
      model.addAttribute("exchange_result", round_till(enteredValue * rate, 1000));
      model.addAttribute("from_to", String.format("1%s/%s%s", toCurrency, round_till(1 / rate, 1000), fromCurrency));
      model.addAttribute("to_from", String.format("1%s/%s%s", fromCurrency, round_till(rate, 1000), toCurrency));
      return "main-page-authorized-new";
    } else if ("rate_history".equals(btnRateHistory)) {
      redirectAttributes.addAttribute("fromCurrency", fromCurrency);
      redirectAttributes.addAttribute("toCurrency", toCurrency);
      redirectAttributes.addAttribute("fromDate", fromDate);
      redirectAttributes.addAttribute("toDate", toDate);
      log.info("Redirect -> /rates");
      return "redirect:/rates";
    } else if ("user_history".equals(btnUserHistory)) {
      log.info("Redirect -> /user-history");
      return "redirect:/user-history";
    } else throw new ServiceUnavailable();
  }
}