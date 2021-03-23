package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.StockQuotesServiceApplication;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.Exchange;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockQuotesServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
class CompanyDAOImplTest {

  @Autowired
  SessionFactory factory;

  @Autowired
  CompanyDAO companyDAO;

  @Autowired
  ExchangeDAO exchangeDAO;

  int nasId;

  int tseId;

  @BeforeEach
  public void initExchange() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into exchange(exchange_name) values" +
			"('NASDAQ'), ('TSE')").executeUpdate();
	nasId = exchangeDAO.getExchange("NASDAQ").getExchangeId();
	tseId = exchangeDAO.getExchange("TSE").getExchangeId();
	NativeQuery query = session.createNativeQuery("insert into company(symbol, exchange_id) values " +
			"('CMP', ?1), ('CMP2', ?1)");
	query.setParameter(1, nasId);
	query.executeUpdate();
  }

  @AfterEach
  public void dropEach() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("""
			delete from expectation;
			delete from users;
			delete from company;
			delete from exchange;
			""").executeUpdate();
  }

  @Test
  public void addCompany() {
	Session session = factory.getCurrentSession();

	Company company1 = new Company();
	company1.setSymbol("CMP1");

	companyDAO.addCompany(company1);
	Query<Company> query = session.createQuery("from Company", Company.class);
	assertEquals(3, query.getResultList().size());
  }

  @Test
  public void getCompany() {
//	Session session = factory.getCurrentSession();
//
//	NativeQuery query = session.createNativeQuery("insert into company(symbol, exchange_id) values " +
//			"('CMP', 1), ('CMP2', 1)");

	Company company1 = companyDAO.getCompanyBySymbol("CMP");
	Company company2 = companyDAO.getCompanyBySymbol("CMP2");
	assertEquals("CMP", company1.getSymbol());
	assertEquals("CMP2", company2.getSymbol());
  }

  @Test
  public void updateCompany() {
	Session session = factory.getCurrentSession();

//	session.createSQLQuery("insert into company(symbol, exchange_id) values " +
//			"('CMP', 1), ('CMP2', 1)").executeUpdate();

	Company company = companyDAO.getCompanyBySymbol("CMP2");
	company.setCompanyName("Test name");
	companyDAO.updateCompany(company);
	Company companyCheck = session
			.createQuery("from Company where symbol = 'CMP2'", Company.class)
			.getSingleResult();
	assertEquals("Test name", companyCheck.getCompanyName());
  }

  @Test
  public void deleteCompany() {
	Session session = factory.getCurrentSession();

//	session.createSQLQuery("insert into company(symbol, exchange_id) values " +
//			"('CMP', 1), ('CMP2', 1)").executeUpdate();
	companyDAO.deleteCompanyBySymbol("CMP2");
	List<Company> companies = session.createQuery("from Company", Company.class).getResultList();
	assertEquals(1, companies.size());
  }

  @Test
  public void getCompaniesBySymbol_getOneCompanyFromTwoCortegeTable_CortegesHasDiffSymbols() {
	Session session = factory.getCurrentSession();

//	session.createSQLQuery("insert into company(symbol, exchange_id) values " +
//			"('CMP', 1), ('CMP2', 1)").executeUpdate();

	Company company = companyDAO.getCompanyBySymbol("CMP");
	assertNotNull(company);
  }

  @Test
  public void addOrUpdateCompany_addNewCompany() {
	Company company = new Company("CMP1");
	Session session = factory.getCurrentSession();

	companyDAO.addOrUpdateCompany(company);
	assertEquals(3, session.createQuery("from Company").getResultList().size());
  }

  @Test
  public void addOrUpdateCompany_updateExistingCompany() {
	Company company = new Company("CMP");
	Session session = factory.getCurrentSession();

	Query<Exchange> query = session.createQuery("from Exchange where id=:id", Exchange.class);
	query.setParameter("id", tseId);
	Exchange exchange = query.getSingleResult();
	company.setExchange(exchange);
	companyDAO.addCompany(company);
	Company updatedCompany = session
			.createQuery("from Company where symbol = 'CMP'", Company.class)
			.getSingleResult();
	assertEquals("TSE", updatedCompany.getExchange().getExchangeName());
  }
}
