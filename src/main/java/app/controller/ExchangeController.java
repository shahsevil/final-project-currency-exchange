package app.controller;

import app.draft.Rate;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

  public Rate handle_exchanges_get() {
    throw new IllegalArgumentException("Should be implemented...");
  }
}
