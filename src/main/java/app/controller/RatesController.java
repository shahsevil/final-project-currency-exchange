package app.controller;

import app.api.currency_exchange.exchange_rates_api_io.range_with_base.HistoricalRates;
import app.entity.User;
import app.exception.RateNotFoundException;
import app.exception.WrongActionException;
import app.service.CurrencyAPIService;
import app.service.CurrencyService;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static app.utils.MathUtils.*;
import static app.utils.Util.getOrDefaultRate;

@Log4j2
@Controller
@RequestMapping("/rates")
public class RatesController {
  private final CurrencyAPIService CURRENCY_API_SERVICE;
  private final CurrencyService CURRENCY_SERVICE;
  private final UserService USER_SERVICE;

  public RatesController(CurrencyAPIService CURRENCY_API_SERVICE,
                         CurrencyService CURRENCY_SERVICE,
                         UserService USER_SERVICE) {
    this.CURRENCY_API_SERVICE = CURRENCY_API_SERVICE;
    this.CURRENCY_SERVICE = CURRENCY_SERVICE;
    this.USER_SERVICE = USER_SERVICE;
  }

  /**
   * http://localhost:8080/rates
   */
  @GetMapping
  public String handle_exchanges_get(@RequestParam(value = "fromCurrency", required = false) String fromCurrency,
                                     @RequestParam(value = "toCurrency", required = false) String toCurrency,
                                     @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
                                     @RequestParam(value = "toDate", required = false) LocalDate toDate,
                                     Model model,
                                     HttpServletRequest httpServletRequest) {

    log.info("fromCurrency is " + fromCurrency);
    log.info("toCurrency is " + toCurrency);
    log.info("fromDate is " + fromDate);
    log.info("toDate is " + toDate);

    final long user_id = (long) httpServletRequest.getSession().getAttribute("user_id");

    Optional<User> user = USER_SERVICE.findUserById(user_id);

    if (user.isPresent()) {

      Optional<HistoricalRates> historicalRates = CURRENCY_API_SERVICE.getRangeWithBase(fromDate, toDate, fromCurrency);

      if (historicalRates.isPresent()) {

        class RateResponse {
          final LocalDate date;
          final String fromCurrency;
          final String toCurrency;
          final double rate;
          final double reverseRate;

          RateResponse(LocalDate date, String fromCurrency, String toCurrency, double rate, double reverseRate) {
            this.date = date;
            this.fromCurrency = fromCurrency;
            this.toCurrency = toCurrency;
            this.rate = rate;
            this.reverseRate = reverseRate;
          }

          public LocalDate getDate() {
            return date;
          }

          public String getFromCurrency() {
            return fromCurrency;
          }

          public String getToCurrency() {
            return toCurrency;
          }

          public double getRate() {
            return rate;
          }

          public double getReverseRate() {
            return reverseRate;
          }

          public String representFrom() {
            return String.format("1%s/%s%s", getFromCurrency(), getRate(), getToCurrency());
          }

          public String representTo() {
            return String.format("1%s/%s%s", getToCurrency(), getReverseRate(), getFromCurrency());
          }
        }

        List<RateResponse> rates = historicalRates.get()
                .rates
                .entrySet()
                .stream()
                .map(e -> {
                  double rate = round_till(getOrDefaultRate(e, toCurrency, 1d), 1000);
                  return new RateResponse(e.getKey(), fromCurrency, toCurrency, rate, round_till(1 / rate, 1000));
                })
                .sorted((r1, r2) -> -r1.date.compareTo(r2.date))
                .collect(Collectors.toList());

        model.addAttribute("rates", rates);
        model.addAttribute("full_name", user.get().getFull_name());
        model.addAttribute("from_date", fromDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
        model.addAttribute("to_date", toDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
        return "rates";
      } else throw new RateNotFoundException();
    } else throw new WrongActionException();
  }

  @PostMapping
  public String handle_rate_history_post(@RequestParam(value = "go_back", required = false) String btnGoBack) {
    log.info("btnGoBack is " + btnGoBack);

    if ("go_back".equals(btnGoBack)) {
      // TODO make logout here
      log.info("Redirect -> /authorized");
      return "redirect:/authorized";
    }
    throw new WrongActionException();
  }
}