package tk.stockquotesservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Andrey Fyodorov
 * Created on 22.03.2021
 */

@Entity
@Table(name = "exchange")
public class Exchange {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "exchange_id")
  private int exchangeId;

  @Column(name = "exchange_name")
  @NotNull(message = "exchangeName must be not null")
  @Size(min = 1, max = 128, message = "exchangeName must be between 1 and 128 characters long")
  private String exchangeName;

  @OneToMany(mappedBy = "exchange", cascade = {
		  CascadeType.PERSIST, CascadeType.DETACH,
		  CascadeType.MERGE, CascadeType.REFRESH})
  private List<Company> companyList;

  public Exchange() {
  }

  public Exchange(String exchangeName) {
    this.exchangeName = exchangeName;
  }

  public int getExchangeId() {
	return exchangeId;
  }

  public String getExchangeName() {
	return exchangeName;
  }

  public void setExchangeName(String exchangeName) {
	this.exchangeName = exchangeName;
  }

  public List<Company> getCompanyList() {
	return companyList;
  }

  @Override
  public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;

	Exchange exchange = (Exchange) o;

	if (exchangeId != exchange.exchangeId) return false;
	return exchangeName.equals(exchange.exchangeName);
  }

  @Override
  public int hashCode() {
	int result = exchangeId;
	result = 31 * result + exchangeName.hashCode();
	return result;
  }
}
