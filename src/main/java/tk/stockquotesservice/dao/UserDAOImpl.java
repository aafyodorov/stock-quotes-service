package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.stockquotesservice.entity.User;

import java.util.Objects;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

@Repository
public class UserDAOImpl implements UserDAO {

  private SessionFactory sessionFactory;

  @Autowired
  public void setLocalSessionFactoryBean(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
  }

  @Override
  public void addUser(User user) {
	Session session = sessionFactory.getCurrentSession();

	session.save(user);
  }

  @Override
  public User getUser(long id) {
	Session session = sessionFactory.getCurrentSession();

	return session.get(User.class, id);
  }

  @Override
  public void updateUser(User user) {
	Session session = sessionFactory.getCurrentSession();

	User tmp = session.get(User.class, user.getId());
	if (tmp == null) {
	  throw new NullPointerException();
	}
	session.update(user);
  }

  @Override
  public void deleteUser(long id) {
	Session session = sessionFactory.getCurrentSession();

	User user = session.get(User.class, id);
	session.delete(Objects.requireNonNull(user));
  }
}
