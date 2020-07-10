package app.api.currency_exchange.exchange_rates_api_io.latest;

import app.api.currency_exchange.exchange_rates_api_io.RRates;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LatestResponse {
  public RRates rates;
  public String base;
  public LocalDate date;
}
