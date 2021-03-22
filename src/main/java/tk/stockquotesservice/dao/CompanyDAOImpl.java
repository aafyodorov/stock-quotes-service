package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.stockquotesservice.entity.Company;

import java.util.Objects;

/**
 * @author Andrey Fyodorov
 * Created on 11.03.2021.
 */

@Repository
public class CompanyDAOImpl implements CompanyDAO {

  private SessionFactory sessionFactory;

  @Autowired
  public void setLocalSessionFactoryBean(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
  }

  @Override
  public void addCompany(Company company) {
	Session session = sessionFactory.getCurrentSession();

	session.saveOrUpdate(company);
  }

  @Override
  public Company getCompanyBySymbol(String symbol) {
	Session session = sessionFactory.getCurrentSession();

	return session.get(Company.class, symbol);
  }

  @Override
  public void updateCompany(Company company) {
	Session session = sessionFactory.getCurrentSession();

	session.update(company);
  }

  @Override
  public void deleteCompanyBySymbol(String symbol) {
	Session session = sessionFactory.getCurrentSession();

	Company user = session.get(Company.class, symbol);
	session.delete(Objects.requireNonNull(user));
  }

  @Override
  public void addOrUpdateCompany(Company company) {
	Session session = sessionFactory.getCurrentSession();

	session.saveOrUpdate(company);
  }

}
