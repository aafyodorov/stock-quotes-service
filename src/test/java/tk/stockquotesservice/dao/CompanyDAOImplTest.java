package tk.stockquotesservice.dao;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.stockquotesservice.StockQuotesServiceApplication;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.MOCK,
	classes = StockQuotesServiceApplication.class
)
class CompanyDAOImplTest {

}