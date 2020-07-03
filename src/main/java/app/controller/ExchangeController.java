package app.controller;

import app.api.currency_exchange.exchange_rates_api_io.latest.LatestResponse;
import app.api.currency_exchange.exchange_rates_api_io.range.RangeResponse;
import app.service.CurrencyAPIService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Log4j2
@Controller
@RequestMapping("/")
public class ExchangeController {
  private final CurrencyAPIService CURRENCY_API_SERVICE;

  public ExchangeController(CurrencyAPIService currency_api_service) {
    CURRENCY_API_SERVICE = currency_api_service;
  }

  /**
   * http://localhost:8080/
   */
  @GetMapping
  public String handle_exchanges_get() {
    log.info("GET -> /");
    LatestResponse resp = CURRENCY_API_SERVICE.obtainLatestRates("USD").get();
    RangeResponse resp2 = CURRENCY_API_SERVICE.obtainRangeRates("EUR", LocalDate.now(), LocalDate.now()).get();
    log.info(resp.base);
    log.info(resp2.allRates);
    return "main-page";
  }

  @PostMapping
  public String handle_post_exchange() {
    throw new IllegalArgumentException("Should be implemented...");
  }
}
