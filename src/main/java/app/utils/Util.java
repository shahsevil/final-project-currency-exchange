package app.utils;

import app.api.currency_exchange.exchange_rates_api_io.latest_with_base.CurrencyList;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.Map;

@Log4j2
public class Util {

  public static <T> T getOrDefault(T coming, T defValue) {
    return coming == null || "default".equals(coming) || coming.equals("") ? defValue : coming;
  }


  public static LocalDate getOrDefaultDate(String coming, LocalDate def) {
//    if (coming == null) return def;
    try {
      log.info("coming is:" + coming + "finish");
      return LocalDate.parse(coming);
    } catch (Exception ex) {
      log.info("returning default date is:" + def);
      return def;
    }
  }

  public static double getOrDefaultRate(Map.Entry<LocalDate, CurrencyList> entry, String toCurr, double def) {
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
