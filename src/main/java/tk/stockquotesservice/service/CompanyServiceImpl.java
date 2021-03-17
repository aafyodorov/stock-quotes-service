package tk.stockquotesservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.dao.CompanyDAO;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;
import tk.stockquotesservice.entity.Expectation;
import tk.stockquotesservice.entity.User;

import java.util.Map;

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
  public void addCompany(Company company) {
    companyDAO.addCompany(company);
  }

  @Override
  @Transactional
  public void addCompanies(Iterable<Company> collection) {
    for (Company company : collection) {
      companyDAO.addCompany(company);
    }
  }

  @Override
  @Transactional
  public Company getCompany(CompanyPK companyPK) {
    return companyDAO.getCompany(companyPK);
  }

  @Override
  @Transactional
  public void updateCompany(Company company) {
	companyDAO.updateCompany(company);
  }

  @Override
  @Transactional
  public void deleteCompany(CompanyPK pk) {
    companyDAO.deleteCompany(pk);
  }

  @Override
  @Transactional
  public Map<User, Expectation> getAllSubscribedUsers(CompanyPK pk) {
    return companyDAO.getCompany(pk).getUsers();
  }
}
