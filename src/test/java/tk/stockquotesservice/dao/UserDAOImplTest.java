package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.StockQuotesServiceApplication;
import tk.stockquotesservice.entity.User;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.MOCK,
	classes = StockQuotesServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
	locations = "classpath:test.properties")
@Transactional
class UserDAOImplTest {

  @Autowired
  SessionFactory factory;

  @Autowired
  UserDAO userDAO;

  @Test
  public void addUser() {
	Session session = factory.getCurrentSession();

	userDAO.addUser(new User(1));
	userDAO.addUser(new User(2));
	userDAO.addUser(new User(3));

	Query<User> query = session.createQuery("from User", User.class);
	Assertions.assertEquals(3, query.getResultList().size());
  }

  @Test
  public void getUser() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into users(user_id, cur_subscribes, max_subscribes) " +
		"values (1, 1, 4), (2, 0, 3)").executeUpdate();

	User user1 = userDAO.getUser(1);
	Assertions.assertEquals(1, user1.getId());
	Assertions.assertEquals(1, user1.getCurSubscribes());
	Assertions.assertEquals(user1.getMaxSubscribes(), 4);

	User user2 = userDAO.getUser(2);
	Assertions.assertEquals(2, user2.getId());
	Assertions.assertEquals(0, user2.getCurSubscribes());
	Assertions.assertEquals(3, user2.getMaxSubscribes());
  }

  @Test
  public void updateUser() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into users(user_id, cur_subscribes, max_subscribes) " +
		"values (1, 1, 4), (2, 0, 3)").executeUpdate();
	User user = session.createQuery("from User where id = 1", User.class).getSingleResult();

	user.setCurSubscribes(5);
	user.setMaxSubscribes(15);
	userDAO.updateUser(user);
	User userCheck = session.createQuery("from User where id = 1", User.class).getSingleResult();
	Assertions.assertEquals(1, userCheck.getId());
	Assertions.assertEquals(5, userCheck.getCurSubscribes());
	Assertions.assertEquals(15, userCheck.getMaxSubscribes());
  }

  @Test
  public void updateUser_NPE() {
	Session session = factory.getCurrentSession();
	User user = new User(999);

	Assertions.assertThrows(NullPointerException.class, () -> userDAO.updateUser(user));
  }

  @Test
  public void deleteUser() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into users(user_id, cur_subscribes, max_subscribes) " +
		"values (1, 1, 4), (2, 0, 3)").executeUpdate();

	userDAO.deleteUser(2);
	List<User> userList = session.createQuery("from User where id = 1", User.class).getResultList();
	Assertions.assertEquals(1, userList.size());
	Assertions.assertEquals(1, userList.get(0).getId());
  }
}