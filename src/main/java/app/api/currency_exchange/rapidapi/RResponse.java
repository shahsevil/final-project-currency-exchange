package app.api.currency_exchange.rapidapi;

import lombok.Value;

@Value
public class RResponse {
  Query query;
  Result result;
  boolean status;
}