package app.api.currency_exchange.exchange_rates_api_io.range;

import app.api.currency_exchange.exchange_rates_api_io.RRates;

import java.time.LocalDate;
import java.util.Collection;

public class RDate {
  LocalDate date;
  Collection<RRates> rRates;
}
