package tk.stockquotesservice.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Andrey Fyodorov
 * Created on 14.03.2021
 */

public class CompanyPK implements Serializable {
  protected String symbol;
  protected String exchange;

  public CompanyPK() {
  }

  public CompanyPK(String symbol, String exchange) {
	this.symbol = symbol;
	this.exchange = exchange;
  }

  @Override
  public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;

	CompanyPK companyPK = (CompanyPK) o;

	if (!Objects.equals(symbol, companyPK.symbol)) return false;
	return Objects.equals(exchange, companyPK.exchange);
  }

  @Override
  public int hashCode() {
	int result = symbol != null ? symbol.hashCode() : 0;
	result = 31 * result + (exchange != null ? exchange.hashCode() : 0);
	return result;
  }
}
