package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
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
import tk.stockquotesservice.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = StockQuotesServiceApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
class CompanyDAOImplTest {

  @Autowired
  SessionFactory factory;

  @Autowired
  CompanyDAO companyDAO;

  @BeforeEach
  public void dropEach() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("""
			delete from expectation;
			delete from users;
			delete from company;
			""").executeUpdate();
  }

  @Test
  public void addCompany() {
	Session session = factory.getCurrentSession();

	Company company1 = new Company();
	company1.setSymbol("CMP1");
	company1.setExchange("NAS");
	Company company2 = new Company();
	company2.setExchange("NAS2");
	company2.setSymbol("CMP2");

	companyDAO.addCompany(company1);
	companyDAO.addCompany(company2);
	Query<Company> query = session.createQuery("from Company", Company.class);
	assertEquals(2, query.getResultList().size());
  }

  @Test
  public void getCompany() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into company(symbol, exchange) values " +
			"('CMP', 'NAS'), ('CMP2', 'EXC')").executeUpdate();

	Company company1 = companyDAO.getCompanyPK(new CompanyPK("CMP", "NAS"));
	Company company2 = companyDAO.getCompanyPK(new CompanyPK("CMP2", "EXC"));
	assertEquals("CMP", company1.getSymbol());
	assertEquals("NAS", company1.getExchange());
	assertEquals("CMP2", company2.getSymbol());
	assertEquals("EXC", company2.getExchange());
  }

  @Test
  public void updateCompany() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into company(symbol, exchange) values " +
			"('CMP', 'NAS'), ('CMP2', 'EXC')").executeUpdate();

	Company company = companyDAO.getCompanyPK(new CompanyPK("CMP2", "EXC"));
	company.setCompanyName("Test name");
	companyDAO.updateCompany(company);
	Company companyCheck = session
			.createQuery("from Company where symbol = 'CMP2' and exchange = 'EXC'", Company.class)
			.getSingleResult();
	assertEquals("Test name", companyCheck.getCompanyName());
  }

  @Test
  public void deleteCompany() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into company(symbol, exchange) values " +
			"('CMP', 'NAS'), ('CMP2', 'EXC')").executeUpdate();
	companyDAO.deleteCompany(new CompanyPK("CMP2", "EXC"));
	List<Company> companies = session.createQuery("from Company", Company.class).getResultList();
	assertEquals(1, companies.size());
	assertEquals("NAS", companies.get(0).getExchange());
  }

  @Test
  public void getCompaniesBySymbol_getEmptyList() {
	List<Company> companies = companyDAO.getCompaniesNySymbol("AAPL");
	assertTrue(companies.isEmpty());
  }

  @Test
  public void getCompaniesBySymbol_getOneCompanyFromTwoCortegeTable_CortegesHasDiffSymbols() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into company(symbol, exchange) values " +
			"('CMP', 'NAS'), ('CMP2', 'EXC')").executeUpdate();

	List<Company> companies = companyDAO.getCompaniesNySymbol("CMP");
	assertEquals(1, companies.size());
  }


  @Test
  public void getCompaniesBySymbol_getOneCompanyFromTwoCortegeTable_CortegesHasSameSymbols() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into company(symbol, exchange) values " +
			"('CMP', 'NAS'), ('CMP', 'EXC')").executeUpdate();

	List<Company> companies = companyDAO.getCompaniesNySymbol("CMP");
	assertEquals(2, companies.size());
  }

  @Test
  public void addOrUpdateCompany_addNewCompany() {
	Company company = new Company("CMP", "NAS");
	Session session = factory.getCurrentSession();

	companyDAO.addOrUpdateCompany(company);
	assertEquals(1, session.createQuery("from Company").getResultList().size());
  }

  @Test
  public void addOrUpdateCompany_addExistingCompany() {
	Company company = new Company("CMP", "NAS");
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into company(symbol, exchange) values " +
			"('CMP', 'NAS'), ('CMP', 'EXC')").executeUpdate();

	company.setExchange("NOT_NAS");
	companyDAO.addCompany(company);
	List<Company> validationCompList = session
			.createQuery("from Company where symbol = 'CMP' and exchange = 'NOT_NAS'", Company.class)
			.getResultList();
	assertEquals(1, validationCompList.size());
	assertEquals("NOT_NAS", validationCompList.get(0).getExchange());
  }
}
