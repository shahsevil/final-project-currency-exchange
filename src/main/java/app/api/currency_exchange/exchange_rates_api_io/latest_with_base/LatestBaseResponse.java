package app.api.currency_exchange.exchange_rates_api_io.latest_with_base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LatestBaseResponse {
  public CurrencyList rates;
  public String base;
  public LocalDate date;
}