package tk.stockquotesservice.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.dao.SymbolDAO;
import tk.stockquotesservice.dao.UserDAO;
import tk.stockquotesservice.entity.Expectation;
import tk.stockquotesservice.entity.Symbol;
import tk.stockquotesservice.entity.User;

import java.util.Map;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

@Service
public class UserServiceImpl implements UserService {

  private UserDAO userDAO;
  private SymbolDAO symbolDAO;

  @Autowired
  public void setSymbolDAO(SymbolDAO symbolDAO) {
	this.symbolDAO = symbolDAO;
  }

  @Autowired
  public void setUserDAO(UserDAO userDAO) {
	this.userDAO = userDAO;
  }

  @Override
  @Transactional
  public void add(@NotNull User user) {
    if (user.getSymbols() != null) {
	  for (Map.Entry<Symbol, Expectation> entry : user.getSymbols().entrySet()) {
		symbolDAO.add(entry.getKey());
	  }
	}
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
