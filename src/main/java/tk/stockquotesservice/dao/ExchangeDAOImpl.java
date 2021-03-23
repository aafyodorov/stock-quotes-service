package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.stockquotesservice.entity.Exchange;

/**
 * @author Andrey Fyodorov
 * Created on 23.03.2021
 */

@Repository
public class ExchangeDAOImpl implements ExchangeDAO {

  private SessionFactory sessionFactory;

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void addExchange(Exchange exchange) {
    Session session = sessionFactory.getCurrentSession();

    session.save(exchange);
  }

  @Override
  public Exchange getExchange(String exchangeName) {
    Session session = sessionFactory.getCurrentSession();

    Query<Exchange> query = session.createQuery("from Exchange where exchangeName=:exName", Exchange.class);
    query.setParameter("exName", exchangeName);
    return query.getSingleResult();
  }

  @Override
  public void deleteExchange(Exchange exchange) {
    Session session = sessionFactory.getCurrentSession();

    session.delete(exchange);
  }
}
