package app.api.currency_exchange.exchange_rates_api_io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RRates {
  public List<RItem> items;
}