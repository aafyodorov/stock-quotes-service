package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.StockQuotesServiceApplication;
import tk.stockquotesservice.entity.Exchange;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = StockQuotesServiceApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
class ExchangeDAOImplTest {

  @Autowired
  ExchangeDAO exchangeDAO;

  @Autowired
  SessionFactory sessionFactory;

  @AfterEach
  public void clearDB() {
	sessionFactory
			.getCurrentSession()
			.createSQLQuery("delete from exchange")
			.executeUpdate();
  }

  @Test
  public void addExchange_addOneExchange() {
	Session session = sessionFactory.getCurrentSession();
	String nasName = "NASDAQ";
	Exchange nas = new Exchange(nasName);

	exchangeDAO.addExchange(nas);
	Query<Exchange> query = session.createQuery("from Exchange where exchangeName=:exName", Exchange.class);
	query.setParameter("exName", nasName);
	Exchange test = query.getSingleResult();
	assertNotNull(test);
	assertEquals("NASDAQ", test.getExchangeName());
  }

  @Test
  public void getExchange_getExistingExchanges() {
	Session session = sessionFactory.getCurrentSession();
	session.createSQLQuery("insert into exchange(exchange_name) values" +
			"('NASDAQ'), ('TSE')").executeUpdate();

	Exchange nas = exchangeDAO.getExchange("NASDAQ");
	Exchange tse = exchangeDAO.getExchange("TSE");
	assertNotNull(nas);
	assertNotNull(tse);
	assertEquals("NASDAQ", nas.getExchangeName());
	assertEquals("TSE", tse.getExchangeName());
  }

  @Test
  public void deleteExchange_deleteExistingExchange() {
	Session session = sessionFactory.getCurrentSession();
	session.createSQLQuery("insert into exchange(exchange_name) values" +
			"('NASDAQ'), ('TSE')").executeUpdate();

	Exchange tse = exchangeDAO.getExchange("TSE");
	exchangeDAO.deleteExchange(tse);
	List<Exchange> exchangeList = session.createQuery("from Exchange", Exchange.class).getResultList();
	assertEquals(1, exchangeList.size());
	assertEquals("NASDAQ", exchangeList.get(0).getExchangeName());
  }
}