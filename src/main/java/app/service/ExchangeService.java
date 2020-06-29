package app.service;

import app.draft.Rate;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class ExchangeService {
  private final RestTemplate rest;
  private final String url = "https://api.exchangeratesapi.io/history?start_at=2019-01-01&end_at=2019-01-31&base=USD";

  public ExchangeService(RestTemplate rest) {
    this.rest = rest;
  }

  public Rate obtain_from_another_server() {
    ResponseEntity<Rate> response = rest.getForEntity(url, Rate.class);
    HttpStatus code = response.getStatusCode();
    Rate body = response.getBody();
    log.info(body);
    log.info(body.getRRates());
    return body;
  }
}
