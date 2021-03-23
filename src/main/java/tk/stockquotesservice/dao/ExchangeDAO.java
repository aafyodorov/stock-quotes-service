package tk.stockquotesservice.dao;

import tk.stockquotesservice.entity.Exchange;

/**
 * @author Andrey Fyodorov
 * Created on 23.03.2021
 */

public interface ExchangeDAO {

  void addExchange(Exchange exchange);

  Exchange getExchange(String exchangeName);

  void deleteExchange(Exchange exchange);
}
