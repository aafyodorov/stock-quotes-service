package tk.stockquotesservice.entity;

import org.junit.jupiter.api.Test;
import tk.stockquotesservice.exception.TooManyCompaniesException;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Test
  void setAddCompany_addFourCompaniesToWatchList_TooManyCompaniesException() {
	User user = new User();
	user.addCompanyToWatchList(new Company("AAPL", "NAS"), 12.33);
	user.addCompanyToWatchList(new Company("YNDX", "NAS"), 15.66);
	user.addCompanyToWatchList(new Company("SBR", "NAS"), 132.80);
	assertThrows(TooManyCompaniesException.class, () -> user.addCompanyToWatchList(new Company(), 1));
	assertEquals(3, user.getCurSubscribes());
	assertEquals(3, user.getCompanies().size());
  }

  @Test
  void deleteCompanyFromWatchList_deleteOneCompany() {
	User user = new User(1);
	Company company = new Company("AAPL", "NAS");
	user.addCompanyToWatchList(company, 12.33);
	user.addCompanyToWatchList(new Company("YNDX", "NAS"), 15.66);
	user.addCompanyToWatchList(new Company("SBR", "NAS"), 132.80);

	user.deleteCompanyFromWatchList(company);
	assertEquals(2, user.getCompanies().size());
  }

  @Test
  void deleteCompanyFromWatchList_deleteCompanyWhichNotInList_NoSuchElementException() {
	User user = new User(1);
	user.addCompanyToWatchList(new Company("AAPL", "NAS"), 12.33);
	user.addCompanyToWatchList(new Company("YNDX", "NAS"), 15.66);
	user.addCompanyToWatchList(new Company("SBR", "NAS"), 132.80);

	assertThrows(NoSuchElementException.class, () ->
			user.deleteCompanyFromWatchList(new Company("I", "NAS")));
  }
}