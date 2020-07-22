package app.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
public class GlobalErrorController implements ErrorController {

  @GetMapping("/error")
  public String handleError(HttpServletRequest request) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    if (status != null) {
      int code = Integer.parseInt(status.toString());
      switch (code) {
        case 302:
          log.error("ERROR -> 302");
          return "error-302";
        case 400:
          log.error("ERROR -> 400");
          return "error-400";
        case 401:
          log.error("ERROR -> 401");
          return "error-401";
        case 403:
          log.error("ERROR -> 403");
          return "error-403";
        case 404:
          log.error("ERROR -> 404");
          return "error-404";
      }
    }
    log.error("ERROR -> 500");
    return "error-500";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}