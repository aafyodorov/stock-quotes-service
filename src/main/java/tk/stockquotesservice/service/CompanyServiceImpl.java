package tk.stockquotesservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.dao.CompanyDAO;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

@Service
public class CompanyServiceImpl implements CompanyService {

  private CompanyDAO companyDAO;

  @Autowired
  public void setCompanyDAO(CompanyDAO companyDAO) {
	this.companyDAO = companyDAO;
  }

  @Override
  @Transactional
  public void add(Company company) {
    companyDAO.addCompany(company);
  }

  @Override
  @Transactional
  public void addCollection(Iterable<Company> collection) {
    for (Company company : collection) {
      companyDAO.addCompany(company);
    }
  }

  @Override
  @Transactional
  public Company get(String symbol, String exchange) {
    return companyDAO.getCompany(new CompanyPK(symbol, exchange));
  }

  @Override
  @Transactional
  public void update(Company company) {
	companyDAO.updateCompany(company);
  }

  @Override
  @Transactional
  public void delete(CompanyPK pk) {
    companyDAO.deleteCompany(pk);
  }
}
