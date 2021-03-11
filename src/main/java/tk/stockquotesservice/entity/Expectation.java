package tk.stockquotesservice.entity;

import javax.persistence.*;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

@Embeddable
public class Expectation {

  @Column(name="exp_price")
  double z;

  @Column(name="exchange")
  String exchange;

  public Expectation() {
  }

  public Expectation(double z, String exchange) {
    this.z = z;
    this.exchange = exchange;
  }

  public double getZ() {
    return z;
  }

  public void setZ(double z) {
    this.z = z;
  }

  public String getExchange() {
    return exchange;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }
}