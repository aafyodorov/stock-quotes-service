package tk.stockquotesservice.service;

import tk.stockquotesservice.entity.Symbol;

/**
 * @author Andrey Fyodorov
 * Created on 12.03.2021.
 */

public interface SymbolService {

  void add(Symbol symbol);
  Symbol getById(int id);
  void update(Symbol symbol);
  void delete(int id);
}
