package app.api.currency_exchange.exchange_rates_api_io.range;

import app.api.currency_exchange.exchange_rates_api_io.RRates;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RDate {
  LocalDate date;
  Collection<RRates> rRates;
}
