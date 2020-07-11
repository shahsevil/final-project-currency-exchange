package app.api.currency_exchange.exchange_rates_api_io.latest_with_base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LatestRates {
  public double CAD;
  public double HKD;
  public double ISK;
  public double PHP;
  public double DKK;
  public double HUF;
  public double CZK;
  public double GBP;
  public double RON;
  public double SEK;
  public double IDR;
  public double INR;
  public double BRL;
  public double RUB;
  public double HRK;
  public double JPY;
  public double THB;
  public double CHF;
  public double EUR;
  public double MYR;
  public double BGN;
  public double TRY;
  public double CNY;
  public double NOK;
  public double NZD;
  public double ZAR;
  public double USD;
  public double MXN;
  public double SGD;
  public double AUD;
  public double ILS;
  public double KRW;
  public double PLN;

  public Stream<Double> streamOfCurr() {
    return Stream.of(CAD, HKD, ISK, PHP, DKK, HUF, CZK, GBP, RON, SEK, IDR, INR, BRL, RUB, HRK, JPY, THB, CHF, EUR, MYR, BGN, TRY, CNY, NOK, NZD, ZAR, USD, MXN, SGD, AUD, ILS, KRW, PLN);
  }

  public Map<String, Double> getAllCurrencyNamesAndValues() {
    Map<String, Double> currencyMap = new TreeMap<>();
    currencyMap.put("CAD", getCAD());
    currencyMap.put("HKD", getHKD());
    currencyMap.put("ISK", getISK());
    currencyMap.put("PHP", getPHP());
    currencyMap.put("DKK", getDKK());
    currencyMap.put("HUF", getHUF());
    currencyMap.put("CZK", getCZK());
    currencyMap.put("AUD", getAUD());
    currencyMap.put("RON", getRON());
    currencyMap.put("SEK", getSEK());
    currencyMap.put("IDR", getIDR());
    currencyMap.put("INR", getINR());
    currencyMap.put("BRL", getBRL());
    currencyMap.put("RUB", getRUB());
    currencyMap.put("HRK", getHRK());
    currencyMap.put("JPY", getJPY());
    currencyMap.put("THB", getTHB());
    currencyMap.put("CHF", getCHF());
    currencyMap.put("SGD", getSGD());
    currencyMap.put("PLN", getPLN());
    currencyMap.put("BGN", getBGN());
    currencyMap.put("TRY", getTRY());
    currencyMap.put("CNY", getCNY());
    currencyMap.put("NOK", getNOK());
    currencyMap.put("NZD", getNZD());
    currencyMap.put("ZAR", getZAR());
    currencyMap.put("USD", getUSD());
    currencyMap.put("MXN", getMXN());
    currencyMap.put("ILS", getILS());
    currencyMap.put("GBP", getGBP());
    currencyMap.put("KRW", getKRW());
    currencyMap.put("MYR", getMYR());
    currencyMap.put("EUR", getEUR());
    return currencyMap;
  }
}