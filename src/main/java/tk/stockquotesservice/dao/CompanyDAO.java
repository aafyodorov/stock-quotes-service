package tk.stockquotesservice.dao;

import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

public interface CompanyDAO {
  void addCompany(Company company);

  Company getCompanyPK(CompanyPK pk);

  Company getCompanyBySymbol(String symbol);

  void updateCompany(Company company);

  void deleteCompany(CompanyPK pk);

  void addOrUpdateCompany(Company company);
}
