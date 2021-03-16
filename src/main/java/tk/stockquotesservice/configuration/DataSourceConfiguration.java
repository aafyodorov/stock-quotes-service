package tk.stockquotesservice.configuration;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Andrey Fyodorov
 * Created on 09.03.2021.
 */

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

  @Value("${tk.stockquotesservice.username}")
  private String PG_USER_STCK;

  @Value("${tk.stockquotesservice.password}")
  private String PG_PWD_STCK;

  @Value("${tk.stockquotesservice.url}")
  private String dbURI;

  @Value("${hibernate.dialect}")
  private String dialect;

  @Value("${jdbc.driverClassName}")
  private String driverClassName;

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	sessionFactory.setDataSource(dataSource());
	sessionFactory.setPackagesToScan("tk.stockquotesservice.entity");
	sessionFactory.setHibernateProperties(hibernateProperties());
	return sessionFactory;
  }

  @Bean
  public DataSource dataSource() {
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName(driverClassName);
	dataSource.setUrl(dbURI);
	dataSource.setUsername(PG_USER_STCK);
	dataSource.setPassword(PG_PWD_STCK);

	return dataSource;
  }

  @Bean
  public PlatformTransactionManager hibernateTransactionManager() {
	HibernateTransactionManager transactionManager
		= new HibernateTransactionManager();
	transactionManager.setSessionFactory(sessionFactory().getObject());
	return transactionManager;
  }

  private Properties hibernateProperties() {
	Properties hibernateProperties = new Properties();
	hibernateProperties.setProperty(
		"hibernate.dialect", dialect);

	return hibernateProperties;
  }
}
