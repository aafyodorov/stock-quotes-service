package tk.stockquotesservice.controllers;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.User;
import tk.stockquotesservice.service.CompanyService;
import tk.stockquotesservice.service.UserService;

import java.util.Objects;

/**
 * @author Andrey Fyodorov
 * Created on 20.03.2021
 */

@Controller
@RequestMapping("/observer")
public class ObserverController {

  private static final Logger logger = LoggerFactory.getLogger(ObserverController.class);

  private UserService userService;

  private CompanyService companyService;

  @Autowired
  public void setUserService(UserService userService) {
	this.userService = userService;
  }

  @Autowired
  public void setCompanyService(CompanyService companyService) {
	this.companyService = companyService;
  }

  //TODO Add symbol verification before trying to get it		https://iextrading.com/trading/eligible-symbols/
  @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<JSONObject> addQuoteToWatchList(@RequestParam long userId,
														@RequestParam String symbol,
														@RequestParam double expPrice) {
	User targetUser;
	Company company = companyService.getCompanyBySymbolIfNotFoundGetThemFromIEX(symbol);
	System.out.println(company);

	try {
	  targetUser = userService.getUser(userId);
	} catch (NullPointerException ex) {
	  targetUser = new User(userId);
	  logger.info("Creating new user: " + userId);
	}
	companyService.addOrUpdateCompany(company);
	targetUser.addCompanyToWatchList(company, expPrice);
	userService.addOrUpdateUser(targetUser);
	return null;
  }
}
