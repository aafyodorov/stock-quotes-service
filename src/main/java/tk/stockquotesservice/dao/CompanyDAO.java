package tk.stockquotesservice.dao;

import tk.stockquotesservice.entity.Company;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

public interface CompanyDAO {
  void addCompany(Company company);

  Company getCompanyBySymbol(String symbol);

  void updateCompany(Company company);

  void deleteCompanyBySymbol(String symbol);

  void addOrUpdateCompany(Company company);
}
