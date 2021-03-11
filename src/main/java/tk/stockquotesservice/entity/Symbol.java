package tk.stockquotesservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

@Entity
@Table(name = "symbol")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "symbol",
    "exchange",
    "name",
    "date",
    "type",
    "iexId",
    "region",
    "currency",
    "isEnabled",
})
public class Symbol implements Serializable {

  @Id
  @Column(name = "symbol_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Size(max = 10)
//  @JsonProperty("symbol")
  private String symbol;

  @Size(max = 10)
//  @JsonProperty("exchange")
  private String exchange;

  @Column (name = "comp_name")
  @Size(max = 128)
//  @JsonProperty("name")
  private String companyName;

  @Column (name = "gen_date")
//  @JsonProperty("date")
  private Date generationDate;

  @Size(max = 10)
//  @JsonProperty("type")
  private String type;

  @Column (name = "iex_id")
  @Size(max = 32)
//  @JsonProperty("iexId")
  private String iexId;

  @Size(max = 10)
//  @JsonProperty("region")
  private String region;

  @Size(max = 3)
//  @JsonProperty("currency")
  private String currency;

  @Column (name = "is_enabled")
//  @JsonProperty("isEnabled")
  private boolean isEnabled;

  public Symbol(String symbol, String exchange, String companyName, Date generationDate, String type, String iexId, String region, String currency, boolean isEnabled) {
    this.symbol = symbol;
    this.exchange = exchange;
    this.companyName = companyName;
    this.generationDate = generationDate;
    this.type = type;
    this.iexId = iexId;
    this.region = region;
    this.currency = currency;
    this.isEnabled = isEnabled;
  }

  public Symbol() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getExchange() {
    return exchange;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Date getGenerationDate() {
    return generationDate;
  }

  public void setGenerationDate(Date generationDate) {
    this.generationDate = generationDate;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getIexId() {
    return iexId;
  }

  public void setIexId(String iexId) {
    this.iexId = iexId;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }
}
