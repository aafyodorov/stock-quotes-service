package tk.stockquotesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class StockQuotesServiceApplication {

  public static void main(String[] args) {
	SpringApplication.run(StockQuotesServiceApplication.class, args);
  }

}
