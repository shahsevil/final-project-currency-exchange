package app.api.currency_exchange.exchange_rates_api_io.latest;

import app.api.currency_exchange.exchange_rates_api_io.RRates;

import java.time.LocalDate;

public class LatestResponse {
  public RRates rates;
  public String base;
  public LocalDate date;
}
