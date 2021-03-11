package tk.stockquotesservice.dao;

import tk.stockquotesservice.entity.Symbol;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

public interface SymbolDAO {
  void add(Symbol symbol);
  Symbol getById(int id);
  void update(Symbol symbol);
  void delete(int id);
}
