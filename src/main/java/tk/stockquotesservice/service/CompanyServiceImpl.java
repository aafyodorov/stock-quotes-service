package tk.stockquotesservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.stockquotesservice.dao.CompanyDAO;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;
import tk.stockquotesservice.entity.Expectation;
import tk.stockquotesservice.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

  private CompanyDAO companyDAO;

  @Autowired
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void setCompanyDAO(CompanyDAO companyDAO) {
	this.companyDAO = companyDAO;
  }

  @Override
  public void addCompany(Company company) {
	companyDAO.addCompany(company);
  }

  @Override
  public void addCompanies(Iterable<Company> collection) {
	for (Company company : collection) {
	  companyDAO.addCompany(company);
	}
  }

  @Override
  public Company getCompanyByPK(CompanyPK companyPK) {
	return companyDAO.getCompanyPK(companyPK);
  }

  @Override
  public void updateCompany(Company company) {
	companyDAO.updateCompany(company);
  }

  @Override
  public void deleteCompany(CompanyPK pk) {
	companyDAO.deleteCompany(pk);
  }

  @Override
  public Map<User, Expectation> getAllSubscribedUsers(CompanyPK pk) {
	return companyDAO.getCompanyPK(pk).getUsers();
  }

  @Override
  public List<Company> getCompaniesBySymbol(String symbol) {
	return companyDAO.getCompaniesNySymbol(symbol);
  }

  @Override
  public void addOrUpdateCompany(Company company) {
	companyDAO.addOrUpdateCompany(company);
  }

}
