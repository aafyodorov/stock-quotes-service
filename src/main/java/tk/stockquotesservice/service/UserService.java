package tk.stockquotesservice.service;

import org.jetbrains.annotations.NotNull;
import tk.stockquotesservice.entity.User;

/**
 * @author Andrey Fyodorov
 * Created on 12.03.2021.
 */

public interface UserService {

  void add(@NotNull User user);
  User getById(int id);
  void update(User user);
  void delete(int id);
}
