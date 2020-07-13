package app.utils;

import app.api.currency_exchange.exchange_rates_api_io.latest_with_base.CurrencyList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class Util {

  public static <T> T getOrDefault(T coming, T defValue) {
    return coming == null || "default".equals(coming) || coming.equals("") ? defValue : coming;
  }


  public static LocalDate getOrDefaultDate(String coming, LocalDate def) {
    try {
      return LocalDate.parse(coming);
    } catch (DateTimeParseException ex) {
      return def;
    }
  }

  public static double getRateOrDefault(Map.Entry<LocalDate, CurrencyList> entry, String toCurr, double def) {
    return entry.getValue()
            .getAllCurrencyNamesAndValues()
            .entrySet()
            .stream()
            .filter(e -> e.getKey().equals(toCurr))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(def);
  }
}
