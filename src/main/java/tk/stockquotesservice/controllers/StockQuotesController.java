package tk.stockquotesservice.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.stockquotesservice.data.Quote;
import tk.stockquotesservice.externalServicesClients.IEXClient;

/**
 * * @author Andrey Fyodorov
 * * Created on 12.02.2021.
 */

@Controller
public class StockQuotesController {

  private IEXClient client;

  @Value("${io.iexcloud.appID}")
  private String token;

  @Autowired
  public void setClient(IEXClient client) {
	this.client = client;
  }

  @GetMapping("/quote")
  ResponseEntity<Quote> getQuotes(@RequestParam(value = "symbol") String symbol) {
	Quote quote = client.getQuotesByTicker(symbol, token);
	return ResponseEntity
			.status(HttpStatus.OK)
			.header(HttpHeaders.CONTENT_TYPE, "application/json")
			.body(quote);
  }

  @PostMapping("/add")
  ResponseEntity<JSONObject> getQuotes(
		  @RequestParam(value = "user_id") int userId,
		  @RequestParam(value = "symbol") String symbol,
		  @RequestParam(value = "exp_price") double exp_price) {
    return null;
  }
}
