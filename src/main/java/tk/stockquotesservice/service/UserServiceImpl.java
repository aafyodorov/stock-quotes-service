package tk.stockquotesservice.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.dao.CompanyDAO;
import tk.stockquotesservice.dao.UserDAO;
import tk.stockquotesservice.entity.User;

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
  public void add(@NotNull User user) {
	userDAO.add(user);
  }

  @Override
  @Transactional
  public User getById(int id) {
    return userDAO.getById(id);
  }

  @Override
  @Transactional
  public void update(User user) {
    userDAO.update(user);
  }

  @Override
  @Transactional
  public void delete(int id) {
	userDAO.delete(id);
  }
}
