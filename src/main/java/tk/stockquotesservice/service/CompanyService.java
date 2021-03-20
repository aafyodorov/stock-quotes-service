package tk.stockquotesservice.service;

import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;
import tk.stockquotesservice.entity.Expectation;
import tk.stockquotesservice.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author Andrey Fyodorov
 * Created on 12.03.2021.
 */

public interface CompanyService {

  void addCompany(Company symbol);

  void addCompanies(Iterable<Company> collection);

  Company getCompany(CompanyPK companyPK);

  void updateCompany(Company symbol);

  void deleteCompany(CompanyPK pk);

  Map<User, Expectation> getAllSubscribedUsers(CompanyPK pk);

  List<Company> getCompaniesBySymbol(String symbol);
}
