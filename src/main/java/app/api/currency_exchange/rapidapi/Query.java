package app.api.currency_exchange.rapidapi;

import lombok.Value;

@Value
public class Query {
  String amount;
  String from;
  String to;
}