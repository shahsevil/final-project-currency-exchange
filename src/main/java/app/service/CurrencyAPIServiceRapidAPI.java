package app.service;

import app.api.currency_exchange.rapidapi.RResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyAPIServiceRapidAPI {
  private final RestTemplate rest;
  private final String url = "https://currency23.p.rapidapi.com/currencyToAll?int=1&base=USD";

  public CurrencyAPIServiceRapidAPI(RestTemplate rest) {
    this.rest = rest;
  }

  private HttpHeaders auth() {
    return new HttpHeaders() {{
      add("x-rapidapi-host", new String("currency23.p.rapidapi.com"));
      add("x-rapidapi-key", new String("b51bec0435mshd42fa8b464f62f9p18299ajsn0f316fa70c99"));
    }};
  }

  private HttpEntity<Object> rqHttpEntity() {
    return new HttpEntity<>(auth());
  }

  public RResponse get_rates() {
    ResponseEntity<RResponse> resp = rest.exchange(url, HttpMethod.GET, rqHttpEntity(), RResponse.class);
    return resp.getBody();
  }
}
