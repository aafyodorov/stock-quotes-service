package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.stockquotesservice.entity.Symbol;

import java.util.Objects;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

@Repository
public class SymbolDAOImpl implements SymbolDAO{

  private SessionFactory sessionFactory;

  @Autowired
  public void setLocalSessionFactoryBean(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
  }

  @Override
  public void add(Symbol symbol) {
	Session session = sessionFactory.getCurrentSession();

	session.save(symbol);
  }

  @Override
  public Symbol getById(int id) {
	Session session = sessionFactory.getCurrentSession();

	return session.get(Symbol.class, id);
  }

  @Override
  public void update(Symbol symbol) {
	Session session = sessionFactory.getCurrentSession();

	Symbol tmp = session.get(Symbol.class, symbol.getId());
	if (tmp == null) {
	  throw new NullPointerException();
	}
	session.save(symbol);
  }

  @Override
  public void delete(int id) {
	Session session = sessionFactory.getCurrentSession();

	Symbol user = session.get(Symbol.class, id);
	session.delete(Objects.requireNonNull(user));
  }
}
