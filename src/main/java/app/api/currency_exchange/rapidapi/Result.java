package app.api.currency_exchange.rapidapi;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Result {
  LocalDateTime cachedTime;
  double value;
}