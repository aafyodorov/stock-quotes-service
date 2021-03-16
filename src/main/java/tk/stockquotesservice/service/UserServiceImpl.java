package tk.stockquotesservice.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.dao.CompanyDAO;
import tk.stockquotesservice.dao.UserDAO;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.Expectation;
import tk.stockquotesservice.entity.User;
import tk.stockquotesservice.exception.TooManyCompaniesException;

import java.util.HashMap;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

//TODO Clean

@Service
public class UserServiceImpl implements UserService {

  private UserDAO userDAO;
  private CompanyDAO companyDAO;

  @Autowired
  public void setSymbolDAO(CompanyDAO companyDAO) {
	this.companyDAO = companyDAO;
  }

  @Autowired
  public void setUserDAO(UserDAO userDAO) {
	this.userDAO = userDAO;
  }

  @Override
  @Transactional
  public void addUser(@NotNull User user) {
	userDAO.addUser(user);
  }

  @Override
  @Transactional
  public User getUser(int id) {
    return userDAO.getUser(id);
  }

  @Override
  @Transactional
  public void updateUser(User user) {
    userDAO.updateUser(user);
  }

  @Override
  @Transactional
  public void deleteUser(int id) {
	userDAO.deleteUser(id);
  }

  @Override
  @Transactional
  public void addStockToWatchList(int userId, Company company, double expPrice) {
    User user = getUser(userId);
    if (user.getCompanies() == null) {
      user.setCompanies(new HashMap<>());
    }
    if (user.getCurSubscribes() == user.getMaxSubscribes()) {
      throw new TooManyCompaniesException("The maximum number of subscriptions (" + user.getMaxSubscribes() +
          ") has been reached, cannot add a new one");
    }
    user.getCompanies().put(company, new Expectation(expPrice));
    updateUser(user);
  }

}
