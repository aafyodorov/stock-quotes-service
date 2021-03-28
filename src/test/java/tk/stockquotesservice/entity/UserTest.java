package tk.stockquotesservice.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.exception.TooManyCompaniesException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
class UserTest {
  List<AbstractMap.SimpleEntry<Company, Double>> expectationList;

  @Autowired
  SessionFactory sessionFactory;

  Exchange nas;

  @BeforeEach
  public void initMap() {
	Session session = sessionFactory.getCurrentSession();
	session.createNativeQuery("insert into exchange(exchange_name) values ('NASDAQ');").executeUpdate();
	nas = session.createQuery("from Exchange ", Exchange.class).getSingleResult();

	expectationList = new ArrayList<>();
	expectationList.add(new AbstractMap.SimpleEntry<>(new Company("AAPL", nas), 12.33));
	expectationList.add(new AbstractMap.SimpleEntry<>(new Company("YNDX", nas), 15.66));
	expectationList.add(new AbstractMap.SimpleEntry<>(new Company("SBR", nas), 132.80));
  }

  @AfterEach
  public void cleanDd() {
	Session session = sessionFactory.getCurrentSession();

	session.createNativeQuery("""
			delete from exchange;
			delete from company;
			""");
  }

  @Test
  public void setAddCompany_addFourCompaniesToWatchList_TooManyCompaniesException() {
	User user = new User();
	for (AbstractMap.SimpleEntry<Company, Double> companyDoubleEntry : expectationList) {
	  user.addCompanyToWatchList(companyDoubleEntry.getKey(), companyDoubleEntry.getValue());
	}
	assertThrows(TooManyCompaniesException.class, () -> user.addCompanyToWatchList(new Company(), 1));
	assertEquals(3, user.getCurSubscribes());
	assertEquals(3, user.getCompanies().size());
  }

  @Test
  public void deleteCompanyFromWatchList_deleteOneCompany() {
	User user = new User(1);
	for (AbstractMap.SimpleEntry<Company, Double> companyDoubleEntry : expectationList) {
	  user.addCompanyToWatchList(companyDoubleEntry.getKey(), companyDoubleEntry.getValue());
	}
	Company company = new Company("AAPL", nas);

	user.deleteCompanyFromWatchList(company);
	assertEquals(2, user.getCompanies().size());
  }

  @Test
  public void deleteCompanyFromWatchList_deleteCompanyWhichNotInList_NoSuchElementException() {
	User user = new User(1);
	for (AbstractMap.SimpleEntry<Company, Double> companyDoubleEntry : expectationList) {
	  user.addCompanyToWatchList(companyDoubleEntry.getKey(), companyDoubleEntry.getValue());
	}

	assertThrows(NoSuchElementException.class, () ->
			user.deleteCompanyFromWatchList(new Company("I", nas)));
  }

  @TestFactory
  public Collection<DynamicTest> isAbleToAddCompany_testFactory() {
	return Arrays.asList(
			DynamicTest.dynamicTest("is able to add company to new user",
					() -> assertTrue(new User(1).isAbleToAddCompany())),
			DynamicTest.dynamicTest("is able to add second company", () -> {
			  User user = new User(1);
			  user.addCompanyToWatchList(expectationList.get(0).getKey(), expectationList.get(0).getValue());
			  assertEquals(user.getCurSubscribes(), 1);
			  assertTrue(user.isAbleToAddCompany());
			}),
			DynamicTest.dynamicTest("is able to add last company", () -> {
			  User user = new User(1);
			  user.addCompanyToWatchList(expectationList.get(0).getKey(), expectationList.get(0).getValue());
			  user.addCompanyToWatchList(expectationList.get(1).getKey(), expectationList.get(1).getValue());
			  assertEquals(user.getCurSubscribes(), 2);
			  assertTrue(user.isAbleToAddCompany());
			}),
			DynamicTest.dynamicTest("is able to add 4th company (expected false)", () -> {
			  User user = new User(1);
			  for (AbstractMap.SimpleEntry<Company, Double> companyDoubleEntry : expectationList) {
				user.addCompanyToWatchList(companyDoubleEntry.getKey(), companyDoubleEntry.getValue());
			  }
			  assertFalse(user.isAbleToAddCompany());
			}));
  }
}
