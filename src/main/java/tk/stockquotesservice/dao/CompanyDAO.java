package tk.stockquotesservice.dao;

import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;

import java.util.List;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

public interface CompanyDAO {
  void addCompany(Company company);

  Company getCompanyPK(CompanyPK pk);

  List<Company> getCompaniesNySymbol(String symbol);

  void updateCompany(Company company);

  void deleteCompany(CompanyPK pk);
}
