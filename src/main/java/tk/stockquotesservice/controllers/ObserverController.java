package tk.stockquotesservice.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.User;
import tk.stockquotesservice.service.CompanyService;
import tk.stockquotesservice.service.UserService;

/**
 * @author Andrey Fyodorov
 * Created on 20.03.2021
 */

@Controller
@RequestMapping("/observer")
public class ObserverController {

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


}
