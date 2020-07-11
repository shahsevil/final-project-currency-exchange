package app.service;

import app.api.currency_exchange.exchange_rates_api_io.eur_to_usd.EurToUsdResponse;
import app.api.currency_exchange.exchange_rates_api_io.latest_with_base.LatestBaseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

@Log4j2
@Service
public class CurrencyAPIService {
  private final RestTemplate rest;

  public CurrencyAPIService(RestTemplate rest) {
    this.rest = rest;
  }

  public Optional<EurToUsdResponse> getLatestEurUSD() {
    String eurToUsdUrl = "https://api.exchangeratesapi.io/latest?symbols=USD";
    try {
      return Optional.ofNullable(rest.getForObject(eurToUsdUrl, EurToUsdResponse.class));
    } catch (Exception x) {
      log.warn("Something went wrong while processing latest eur to usd rate" + x);
      return Optional.empty();
    }
  }

  public Optional<LatestBaseResponse> getLatestRatesWithBase(String currFrom) {
    String latestFromTo = String.format("https://api.exchangeratesapi.io/latest?base=%s", currFrom);
    try {
      return Optional.ofNullable(rest.getForObject(latestFromTo, LatestBaseResponse.class));
    } catch (Exception x) {
      log.warn("Something went wrong while processing latest with base " + x);
      return Optional.empty();
    }
  }
}
