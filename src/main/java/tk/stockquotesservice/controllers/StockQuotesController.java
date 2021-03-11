package tk.stockquotesservice.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.stockquotesservice.dao.UserDAO;
import tk.stockquotesservice.dao.UserDAOImpl;
import tk.stockquotesservice.data.Quote;
import tk.stockquotesservice.entity.Expectation;
import tk.stockquotesservice.entity.Symbol;
import tk.stockquotesservice.entity.User;
import tk.stockquotesservice.externalServicesClients.IEXClient;
import tk.stockquotesservice.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
  UserService userService;

  @Autowired
  SessionFactory sessionFactory;


  @Autowired
  public void setClient(IEXClient client) {
	this.client = client;
  }

  @GetMapping("/")
  ResponseEntity<String> getQuotes(@RequestParam(value = "symbol") String symbol) {

    Symbol symbol1 = new Symbol("AAPL", "NASDAQ", "Apple inc",
        new Date(), "type1", "132", "US", "USD", true);
    Map<Symbol, Expectation> map = new HashMap<>();
    map.put(symbol1, new Expectation(123.56, symbol1.getExchange()));
    User user = new User();
    user.setId(123456);
//    Session session = sessionFactory.getCurrentSession();
    user.setSymbols(map);
//    session.beginTransaction();
//    session.save(user);
//    session.getTransaction().commit();
    userService.add(user);

    return null;
//    Quote quote = client.getQuotesByTicker(symbol, token);
//    return ResponseEntity
//        .status(HttpStatus.OK)
//        .header(HttpHeaders.CONTENT_TYPE, "application/json")
//        .body(mapQuotesToJson(quote).toString());
  }

  private JSONObject mapQuotesToJson(Quote quote) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("symbol", quote.getSymbol());
    jsonObject.put("companyName", quote.getCompanyName());
    jsonObject.put("primaryExchange", quote.getPrimaryExchange());
    jsonObject.put("iexOpen", quote.getIexOpen());
    jsonObject.put("iexOpenTime", quote.getIexOpenTime());
    jsonObject.put("latestSource", quote.getLatestSource());
    jsonObject.put("latestPrice", quote.getLatestPrice());
    jsonObject.put("latestUpdate", quote.getLatestUpdate());
    jsonObject.put("latestVolume", quote.getLatestVolume());
    return jsonObject;
  }
}
