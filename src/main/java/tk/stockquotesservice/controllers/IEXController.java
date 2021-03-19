package tk.stockquotesservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.stockquotesservice.data.Quote;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.externalServicesClients.IEXClient;

/**
 * * @author Andrey Fyodorov
 * * Created on 12.02.2021.
 */

@Controller
@RequestMapping("/iex")
public class IEXController {

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

  @GetMapping("/company")
  ResponseEntity<Company> getCompany(@RequestParam(value = "symbol") String symbol) {
	Company company = client.getCompany(symbol, token);
	System.out.println(company);
	return ResponseEntity
			.status(HttpStatus.OK)
			.header(HttpHeaders.CONTENT_TYPE, "application/json")
			.body(company);
  }

}
