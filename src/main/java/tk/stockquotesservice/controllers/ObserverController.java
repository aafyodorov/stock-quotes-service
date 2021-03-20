package tk.stockquotesservice.controllers;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Andrey Fyodorov
 * Created on 20.03.2021
 */

@Controller
@RequestMapping("/observer")
public class ObserverController {

  @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
  public RequestEntity<JSONObject> addQuoteToWatchList(@RequestParam long userId,
													   @RequestParam String symbol,
													   @RequestParam String expPrice) {
    return null;
  }
}
