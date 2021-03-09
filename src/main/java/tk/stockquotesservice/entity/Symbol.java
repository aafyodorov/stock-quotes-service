package tk.stockquotesservice.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

@Entity
@Table(name = "symbol")
public class Symbol {

  @Id
  @Column(name = "symbol_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String symbol;

  private String exchange;

  @Column (name = "comp_name")
  private String companyName;

  @Column (name = "gen_date")
  private Date generationDate;

  private String type;

  @Column (name = "iexid")
  private String iexId;

  private String region;

  private String currency;

  @Column (name = "isenabled")
  private boolean isEnabled;
}
