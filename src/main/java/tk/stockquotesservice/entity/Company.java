package tk.stockquotesservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author Andrey Fyodorov
 * Created on 13.03.2021
 */

@Entity
@Table(name = "company")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"symbol",
		"companyName",
		"exchange",
		"industry",
		"website",
		"securityName",
		"issueType",
		"sector",
		"country",
})
public class Company {

  @Id
  @Size(min = 1, max = 10, message = "Symbol field length must be between 1 and 10 characters")
  private String symbol;

  @Column(name = "company_name")
  private String companyName;

  @ManyToOne(cascade = {
		  CascadeType.PERSIST, CascadeType.DETACH,
		  CascadeType.MERGE, CascadeType.REFRESH})
  @JoinColumn(name = "exchange_id")
  private Exchange exchange;

  private String industry;

  private String website;

  @Column(name = "security_name")
  private String securityName;

  @Column(name = "issue_type")
  private String issueType;

  private String sector;

  private String country;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
		  name = "expectation",
		  joinColumns = {@JoinColumn(name = "symbol", referencedColumnName = "symbol")})
  @MapKeyJoinColumn(name = "user_id")
  @Column(name = "exp_price")
  @JsonIgnore
  private Map<User, Expectation> users;

  public Company() {
  }

  public Company(String symbol) {
	this.symbol = symbol;
  }

  public String getSymbol() {
	return symbol;
  }

  public void setSymbol(String symbol) {
	this.symbol = symbol;
  }

  public String getCompanyName() {
	return companyName;
  }

  public void setCompanyName(String companyName) {
	this.companyName = companyName;
  }

  public Exchange getExchange() {
	return exchange;
  }

  public void setExchange(Exchange exchange) {
	this.exchange = exchange;
  }

  public String getIndustry() {
	return industry;
  }

  public void setIndustry(String industry) {
	this.industry = industry;
  }

  public String getWebsite() {
	return website;
  }

  public void setWebsite(String website) {
	this.website = website;
  }

  public String getSecurityName() {
	return securityName;
  }

  public void setSecurityName(String securityName) {
	this.securityName = securityName;
  }

  public String getIssueType() {
	return issueType;
  }

  public void setIssueType(String issueType) {
	this.issueType = issueType;
  }

  public String getSector() {
	return sector;
  }

  public void setSector(String sector) {
	this.sector = sector;
  }

  public String getCountry() {
	return country;
  }

  public void setCountry(String country) {
	this.country = country;
  }

  public void setUsers(Map<User, Expectation> users) {
	this.users = users;
  }

  public Map<User, Expectation> getUsers() {
	return users;
  }

  @Override
  public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;

	Company company = (Company) o;

	if (!symbol.equals(company.symbol)) return false;
	return exchange.equals(company.exchange);
  }

  @Override
  public int hashCode() {
	int result = symbol.hashCode();
	result = 31 * result + exchange.hashCode();
	return result;
  }

  @Override
  public String toString() {
	return String.format("""
					Company name:	%s (%s)
					Exchange:		%s
					Country:		%s
					Industry:		%s
					Sector:			%s
					Web site:		%s
					""", companyName, symbol,
			exchange.getExchangeName(),
			country,
			industry,
			sector,
			website);
  }

}
