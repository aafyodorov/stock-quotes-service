package tk.stockquotesservice.entity;

import tk.stockquotesservice.exception.TooManyCompaniesException;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

@Entity
@Table(name = "users")
public class User {

  @Id
  @Column(name = "user_id")
  private int id;

  @Column(name = "cur_subscribes")
  private int curSubscribes;

  @Column(name = "max_subscribes")
  private int maxSubscribes;

  @ElementCollection
  @CollectionTable(name = "expectation", joinColumns = @JoinColumn(name = "user_id"))
  @MapKeyJoinColumns(value = {@MapKeyJoinColumn(name = "exchange"), @MapKeyJoinColumn(name = "symbol")})
  @Column(name = "exp_price")
  private Map<Company, Expectation> companies;

  public User() {
	maxSubscribes = 3;
  }

  public User(int id) {
	this();
	this.id = id;
  }

  public int getId() {
	return id;
  }

  public void setId(int id) {
	this.id = id;
  }

  public int getCurSubscribes() {
	return curSubscribes;
  }

  public int getMaxSubscribes() {
	return maxSubscribes;
  }

  public void setMaxSubscribes(int maxSubscribes) {
	this.maxSubscribes = maxSubscribes;
  }

  public Map<Company, Expectation> getCompanies() {
	return companies;
  }

  public void addCompanyToWatchList(Company company, double expPrice) {
	if (!this.isAbleToAddCompany()) {
	  throw new TooManyCompaniesException("The maximum number of subscriptions (" + maxSubscribes +
			  ") has been reached, cannot add a new one");
	} else if (companies == null) {
	  companies = new HashMap<>();
	}
	companies.put(company, new Expectation(expPrice));
	++curSubscribes;
  }

  public void deleteCompanyFromWatchList(Company company) {
	if (companies.get(company) == null) {
	  throw new NoSuchElementException(
			  String.format("User %d is not subscribed to company: (%s, %s)\n",
					  id, company.getSymbol(), company.getExchange()));
	}
	companies.remove(company);
  }

  public boolean isAbleToAddCompany() {
	return curSubscribes < maxSubscribes;
  }

  @Override
  public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;

	User user = (User) o;

	if (id != user.id) return false;
	if (curSubscribes != user.curSubscribes) return false;
	return maxSubscribes == user.maxSubscribes;
  }

  @Override
  public int hashCode() {
	int result = id;
	result = 31 * result + curSubscribes;
	result = 31 * result + maxSubscribes;
	return result;
  }

  @Override
  public String toString() {
	return "User{" +
			"id=" + id +
			", curSubscribes=" + curSubscribes +
			", maxSubscribes=" + maxSubscribes +
			'}';
  }
}
