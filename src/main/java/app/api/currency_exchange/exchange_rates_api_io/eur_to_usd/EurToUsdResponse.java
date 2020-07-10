package app.api.currency_exchange.exchange_rates_api_io.eur_to_usd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EurToUsdResponse {
  public EURates rates;
  public String base;
  public LocalDate date;

  public EurToUsdResponse(EURates rates, String base, LocalDate date) {
    this.rates = rates;
    this.base = base;
    this.date = date;
  }

  public EurToUsdResponse() {
  }
}
