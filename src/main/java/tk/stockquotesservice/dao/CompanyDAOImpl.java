package tk.stockquotesservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.stockquotesservice.entity.Company;
import tk.stockquotesservice.entity.CompanyPK;

import java.util.List;
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

	session.save(company);
  }

  @Override
  public Company getCompanyPK(CompanyPK pk) {
	Session session = sessionFactory.getCurrentSession();

	return session.get(Company.class, pk);
  }

  @Override
  public void updateCompany(Company company) {
	Session session = sessionFactory.getCurrentSession();

	Company tmp = session.get(Company.class, new CompanyPK(company.getSymbol(), company.getExchange()));
	if (tmp == null) {
	  throw new NullPointerException();
	}
	session.update(company);
  }

  @Override
  public void deleteCompany(CompanyPK pk) {
	Session session = sessionFactory.getCurrentSession();

	Company user = session.get(Company.class, pk);
	session.delete(Objects.requireNonNull(user));
  }

  @Override
  public List<Company> getCompaniesNySymbol(String symbol) {
	Session session = sessionFactory.getCurrentSession();

	Query<Company> query = session.createQuery("from Company c where c.symbol=:sym", Company.class);
	query.setParameter("sym", symbol);
	return query.getResultList();
  }
}
