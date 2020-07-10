package app.api.currency_exchange.exchange_rates_api_io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RItem {
  public double rate;
}
