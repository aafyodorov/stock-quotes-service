package tk.stockquotesservice.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.StockQuotesServiceApplication;
import tk.stockquotesservice.dao.CompanyDAO;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.Expectation;
import tk.stockquotesservice.entity.User;
import tk.stockquotesservice.externalServicesClients.IEXClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockQuotesServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
class CompanyServiceImplTest {

  @Autowired
  SessionFactory sessionFactory;

  @Autowired
  CompanyService companyService;

  @Autowired
  UserService userService;

  @Test
  public void getAllSubscribedUsers() {
	Company company = new Company("CMP", "NAS");
	User user1 = new User(1);
	User user2 = new User(2);
	User user3 = new User(3);

	companyService.addCompany(company);
	user1.addCompanyToWatchList(company, 123.22);
	user2.addCompanyToWatchList(company, 123.22);
	user3.addCompanyToWatchList(company, 123.22);
	userService.addUser(user1);
	userService.addUser(user2);
	userService.addUser(user3);

	Map<User, Expectation> map = companyService.getAllSubscribedUsers(company.getSymbol());
	assertEquals(3, map.size());
	assertTrue(map.containsKey(user1));
	assertTrue(map.containsKey(user2));
	assertTrue(map.containsKey(user3));
  }

}
