package tk.stockquotesservice.controllers;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Andrey Fyodorov
 * Created on 18.03.2021
 */

@Controller
@RequestMapping("/")
public class DataManipulationController {

  @PostMapping("/add")
  ResponseEntity<JSONObject> getQuotes(
		  @RequestParam(value = "user_id") int userId,
		  @RequestParam(value = "symbol") String symbol,
		  @RequestParam(value = "exp_price") double exp_price) {

	return null;
  }
}
