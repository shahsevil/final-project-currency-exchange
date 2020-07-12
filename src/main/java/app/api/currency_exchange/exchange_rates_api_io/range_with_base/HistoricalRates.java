package app.api.currency_exchange.exchange_rates_api_io.range_with_base;

import app.api.currency_exchange.exchange_rates_api_io.latest_with_base.CurrencyList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalRates {
  public Map<String, CurrencyList> rates;
  public String base;
  public LocalDate start_at;
  public LocalDate end_at;

  public HistoricalRates(Map<String, CurrencyList> rates, String base, LocalDate start_at, LocalDate end_at) {
    this.rates = rates;
    this.base = base;
    this.start_at = start_at;
    this.end_at = end_at;
  }

  public HistoricalRates() {
  }
}