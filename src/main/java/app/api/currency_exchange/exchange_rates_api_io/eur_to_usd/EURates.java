package app.api.currency_exchange.exchange_rates_api_io.eur_to_usd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EURates {
  public double USD;

  public EURates(double USD) {
    this.USD = USD;
  }

  public EURates() {
  }
}
