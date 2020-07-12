package app.controller;

import app.service.CurrencyAPIService;
import app.service.CurrencyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@Log4j2
@Controller
@RequestMapping("/rates")
public class RatesController {
  private final CurrencyAPIService CURRENCY_API_SERVICE;
  private final CurrencyService CURRENCY_SERVICE;

  public RatesController(CurrencyAPIService currency_api_service, CurrencyService currency_service) {
    CURRENCY_API_SERVICE = currency_api_service;
    CURRENCY_SERVICE = currency_service;
  }

  /**
   * http://localhost:8080/rates
   */
  @GetMapping
  public String handle_exchanges_get(Model model) {


    model.addAttribute("name_and_surname", "Elvin Taghizade");
    model.addAttribute("from_date", LocalDate.now());
    model.addAttribute("to_date", LocalDate.now());
    model.addAttribute("rates", Collections.emptyList());

    return "rates";
  }
}