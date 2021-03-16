package tk.stockquotesservice.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.StockQuotesServiceApplication;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;
import tk.stockquotesservice.entity.Expectation;
import tk.stockquotesservice.entity.User;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.MOCK,
	classes = StockQuotesServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
class UserServiceImplTest {

  @Autowired
  SessionFactory sessionFactory;

  @Autowired
  UserService userService;

  @Test
  public void addStockToWatchList_checkAddToAllTables() {
	Session session = sessionFactory.getCurrentSession();
	Company company1 = new Company(new CompanyPK("AAPL","NAS"));
	Company company2 = new Company(new CompanyPK("YNDX","NAS"));

	userService.addStockToWatchList(1, company1, 123.25);
	userService.addStockToWatchList(1, company2, 444.99);

	Map<Company, Expectation> companyExpectationMap = session.get(User.class, 1).getCompanies();
	assertEquals(2, companyExpectationMap.size());
	assertEquals(123.25, companyExpectationMap.get(company1).getExpectedPrice());
	assertEquals(444.99, companyExpectationMap.get(company2).getExpectedPrice());
  }

  @Test
  public void addStockToWatchList_addThreeCompanies() {
	Company company1 = new Company(new CompanyPK("AAPL","NAS"));
	Company company2 = new Company(new CompanyPK("YNDX","NAS"));
	Company company3 = new Company(new CompanyPK("SBR","NAS"));

	userService.addStockToWatchList(1, company1, 123.25);
	userService.addStockToWatchList(1, company2, 444.99);
	userService.addStockToWatchList(1, company3, 444.99);
	assertEquals(3, userService.getUser(1).getCurSubscribes());
  }
}