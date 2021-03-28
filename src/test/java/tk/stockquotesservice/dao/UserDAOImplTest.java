package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
import tk.stockquotesservice.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = StockQuotesServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
class UserDAOImplTest {

  @Autowired
  SessionFactory factory;

  @Autowired
  UserDAO userDAO;

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
  public void addUser() {
	Session session = factory.getCurrentSession();

	userDAO.addUser(new User(1));
	userDAO.addUser(new User(2));
	userDAO.addUser(new User(3));

	Query<User> query = session.createQuery("from User", User.class);
	assertEquals(3, query.getResultList().size());
  }

  @Test
  public void getUser() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into users(user_id, cur_subscribes, max_subscribes) " +
			"values (1, 1, 4), (2, 0, 3)").executeUpdate();

	User user1 = userDAO.getUser(1);
	assertEquals(1, user1.getId());
	assertEquals(1, user1.getCurSubscribes());
	assertEquals(user1.getMaxSubscribes(), 4);

	User user2 = userDAO.getUser(2);
	assertEquals(2, user2.getId());
	assertEquals(0, user2.getCurSubscribes());
	assertEquals(3, user2.getMaxSubscribes());
  }

  @Test
  public void updateUser() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into users(user_id, cur_subscribes, max_subscribes) " +
			"values (1, 1, 4), (2, 0, 3)").executeUpdate();
	User user = session.createQuery("from User where id = 1", User.class).getSingleResult();

	user.setMaxSubscribes(15);
	userDAO.updateUser(user);
	User userCheck = session.createQuery("from User where id = 1", User.class).getSingleResult();
	assertEquals(1, userCheck.getId());
	assertEquals(15, userCheck.getMaxSubscribes());
  }

  @Test
  public void deleteUser() {
	Session session = factory.getCurrentSession();

	session.createSQLQuery("insert into users(user_id, cur_subscribes, max_subscribes) " +
			"values (1, 1, 4), (2, 0, 3)").executeUpdate();

	userDAO.deleteUser(2L);
	List<User> userList = session.createQuery("from User", User.class).getResultList();
	assertEquals(1, userList.size());
	assertEquals(1, userList.get(0).getId());
  }

  @Test
  public void addOrUpdateUser_addNewUser() {
    User user = new User(1);
	Session session = factory.getCurrentSession();

    userDAO.addOrUpdateUser(user);
    assertEquals(1, session.createQuery("from User ").getResultList().size());
  }

  @Test
  public void addOrUpdateUser_updateExistingUser() {
	User user = new User(1);
	Session session = factory.getCurrentSession();

	user.setMaxSubscribes(10);
	session.createSQLQuery("insert into users(user_id, cur_subscribes, max_subscribes) " +
			"values (1, 1, 4)").executeUpdate();

	userDAO.addOrUpdateUser(user);
	User validUser = session.createQuery("from User where id = 1", User.class).getSingleResult();
	assertEquals(1, validUser.getId());
	assertEquals(10, validUser.getMaxSubscribes());
  }
}