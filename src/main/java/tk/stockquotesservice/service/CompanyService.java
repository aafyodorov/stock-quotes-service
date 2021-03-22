package tk.stockquotesservice.service;

import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.Expectation;
import tk.stockquotesservice.entity.User;

import java.util.Map;

/**
 * @author Andrey Fyodorov
 * Created on 12.03.2021.
 */

public interface CompanyService {

  void addCompanies(Iterable<Company> collection);

  void addCompany(Company company);

  Company getCompanyBySymbol(String symbol);

  void updateCompany(Company company);

  void deleteCompanyBySymbol(String symbol);

  Map<User, Expectation> getAllSubscribedUsers(String symbol);

  Company getCompanyBySymbolIfNotFoundGetThemFromIEX(String symbol);

  void addOrUpdateCompany(Company company);
}
