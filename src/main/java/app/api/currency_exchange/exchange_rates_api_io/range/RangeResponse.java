package app.api.currency_exchange.exchange_rates_api_io.range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RangeResponse {
  public RAllRates allRates;
  public LocalDate start_at;
  public String currName;
  public LocalDate end_at;
}
