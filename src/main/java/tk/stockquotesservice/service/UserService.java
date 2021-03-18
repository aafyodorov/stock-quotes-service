package tk.stockquotesservice.service;

import org.jetbrains.annotations.NotNull;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.User;

/**
 * @author Andrey Fyodorov
 * Created on 12.03.2021.
 */

public interface UserService {

  void addUser(@NotNull User user);

  User getUser(int id);

  void updateUser(User user);

  void deleteUser(int id);

  void addStockToWatchList(int userId, Company company, double expPrice);
}
