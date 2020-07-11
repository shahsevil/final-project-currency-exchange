package app.api.currency_exchange.exchange_rates_api_io.latest_with_base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.util.List;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class LatestRates2 {
  public List<Double> rates;
}
