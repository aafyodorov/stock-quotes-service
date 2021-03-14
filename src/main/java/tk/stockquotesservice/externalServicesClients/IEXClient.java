package tk.stockquotesservice.externalServicesClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tk.stockquotesservice.data.Quote;

/**
 * * @author Andrey Fyodorov
 * * Created on 12.02.2021.
 */

@FeignClient(name = "IEXClient", url = "${io.iexcloud.apiURI}")
public interface IEXClient {
  @GetMapping("${io.iexcloud.quote}")
  Quote getQuotesByTicker(@PathVariable(value = "symbol") String symbol,
						  @RequestParam (value = "token") String token);

}
