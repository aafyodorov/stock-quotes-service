package tk.stockquotesservice.dao;

import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;
import tk.stockquotesservice.entity.Expectation;
import tk.stockquotesservice.entity.User;

import java.util.Map;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

public interface CompanyDAO {
  void addCompany(Company company);

  Company getCompany(CompanyPK pk);

  void updateCompany(Company company);

  void deleteCompany(CompanyPK pk);
}
