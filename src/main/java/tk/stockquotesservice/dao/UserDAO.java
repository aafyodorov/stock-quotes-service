package tk.stockquotesservice.dao;

import tk.stockquotesservice.entity.User;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

public interface UserDAO {
  void add(User user);
  User getById(int id);
  void update(User user);
  void delete(int id);
}
