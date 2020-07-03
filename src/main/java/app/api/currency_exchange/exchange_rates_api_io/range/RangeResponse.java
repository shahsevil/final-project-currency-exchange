package app.api.currency_exchange.exchange_rates_api_io.range;

import java.time.LocalDate;

public class RangeResponse {
  public RAllRates allRates;
  public LocalDate start_at;
  public String currName;
  public LocalDate end_at;
}
