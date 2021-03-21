package tk.stockquotesservice.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.dao.CompanyDAO;
import tk.stockquotesservice.dao.UserDAO;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.User;

import java.util.Objects;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private UserDAO userDAO;
  private CompanyDAO companyDAO;

  @Autowired
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void setUserDAO(UserDAO userDAO) {
	this.userDAO = userDAO;
  }

  @Autowired
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void CompanyService(CompanyDAO companyDAO) {
	this.companyDAO = companyDAO;
  }

  @Override
  public void addUser(@NotNull User user) {
	userDAO.addUser(user);
  }

  @Override
  public User getUser(long id) {
	return Objects.requireNonNull(userDAO.getUser(id));
  }

  @Override
  public void updateUser(User user) {
	userDAO.updateUser(user);
  }

  @Override
  public void deleteUser(int id) {
	userDAO.deleteUser(id);
  }

  @Override
  public void addStockToWatchList(int userId, Company company, double expPrice) {
	User user;
	try {
	  user = getUser(userId);
	} catch (NullPointerException ex) {
	  user = new User(userId);
	  userDAO.addUser(user);
	}
	if (companyDAO.getCompanyPK(company.getPk()) == null) {
	  companyDAO.addCompany(company);
	}
	user.addCompanyToWatchList(company, expPrice);
	updateUser(user);
  }

  @Override
  public void addOrUpdateUser(User user) {
    userDAO.addOrUpdateUser(user);
  }
}
