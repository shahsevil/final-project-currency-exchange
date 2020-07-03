package app.service;

import app.api.currency_exchange.exchange_rates_api_io.latest.LatestResponse;
import app.api.currency_exchange.exchange_rates_api_io.range.RangeResponse;
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

  public Optional<LatestResponse> obtainLatestRates(String currFrom) {
    String latestUrl = String.format("https://api.exchangeratesapi.io/latest?base=%s", currFrom);
    try {
      return Optional.ofNullable(rest.getForObject(latestUrl, LatestResponse.class));
    } catch (Exception x) {
      log.warn("Something went wrong while processing range rates");
      return Optional.empty();
    }
  }

  public Optional<RangeResponse> obtainRangeRates(String currFrom, LocalDate dateFrom, LocalDate dateTo) {
    String rangeUrl = String.format("https://api.exchangeratesapi.io/history?start_at=%s&end_at=%s&base=%s", dateFrom, dateTo, currFrom);
    try {
      return Optional.ofNullable(rest.getForObject(rangeUrl, RangeResponse.class));
    } catch (Exception x) {
      log.warn("Something went wrong while processing range rates");
      return Optional.empty();
    }
  }

}
