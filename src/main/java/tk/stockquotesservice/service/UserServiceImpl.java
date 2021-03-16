package tk.stockquotesservice.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.dao.CompanyDAO;
import tk.stockquotesservice.dao.UserDAO;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.User;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

@Service
public class UserServiceImpl implements UserService {

  private UserDAO userDAO;
  private CompanyDAO companyDAO;

  @Autowired
  public void setUserDAO(UserDAO userDAO) {
	this.userDAO = userDAO;
  }

  @Autowired
  public void CompanyService(CompanyDAO companyDAO) {
    this.companyDAO = companyDAO;
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
    if (user == null) {
      user = new User(userId);
      userDAO.addUser(user);
    }
    if (companyDAO.getCompany(company.getPk()) == null) {
      companyDAO.addCompany(company);
    }
    user.addCompanyToWatchList(company, expPrice);
    updateUser(user);
  }

}
