package app.api.currency_exchange.exchange_rates_api_io.range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Collection;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RAllRates {
  Collection<RDate> rDates;
}
