package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.entity.User;

import java.util.Objects;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

@Repository
public class UserDAOImpl implements UserDAO{

  private SessionFactory sessionFactory;

  @Autowired
  public void setLocalSessionFactoryBean(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
  }

  @Override
  @Transactional
  public void add(User user) {
    Session session = sessionFactory.getCurrentSession();

	session.save(user);
  }

  @Override
  @Transactional
  public User getById(int id) {
	Session session = sessionFactory.getCurrentSession();

	return session.get(User.class, id);
  }

  @Override
  @Transactional
  public void update(User user) {
	Session session = sessionFactory.getCurrentSession();

	User tmp = session.get(User.class, user.getId());
	if (tmp == null) {
	  throw new NullPointerException();
	}
	session.save(user);
  }

  @Override
  @Transactional
  public void delete(int id) {
	Session session = sessionFactory.getCurrentSession();

	User user = session.get(User.class, id);
	session.delete(Objects.requireNonNull(user));
  }
}
