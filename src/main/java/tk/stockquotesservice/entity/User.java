package tk.stockquotesservice.entity;

import javax.persistence.*;
import java.util.Map;

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
  @MapKeyJoinColumns(value = {@MapKeyJoinColumn (name = "exchange"), @MapKeyJoinColumn (name = "symbol")})
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

  public void setCurSubscribes(int curSubscribes) {
	this.curSubscribes = curSubscribes;
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

  public void setCompanies(Map<Company, Expectation> companies) {
	this.companies = companies;
  }
}
