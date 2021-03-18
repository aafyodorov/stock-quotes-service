package tk.stockquotesservice.entity;

import javax.persistence.*;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

@Embeddable
public class Expectation {

  @Column(name = "exp_price")
  double expectedPrice;

  public Expectation() {
  }

  public Expectation(double expectedPrice) {
	this.expectedPrice = expectedPrice;
  }

  public Expectation(double expectedPrice, String exchange) {
	this.expectedPrice = expectedPrice;
  }

  public double getExpectedPrice() {
	return expectedPrice;
  }

  public void setExpectedPrice(double expectedPrice) {
	this.expectedPrice = expectedPrice;
  }

}