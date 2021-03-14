package tk.stockquotesservice.dao;

import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

public interface CompanyDAO {
  void add(Company company);
  Company get(CompanyPK pk);
  void update(Company company);
  void delete(int id);
}
