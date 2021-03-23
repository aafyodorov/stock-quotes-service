//package tk.stockquotesservice.entity;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DynamicTest;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestFactory;
//import tk.stockquotesservice.exception.TooManyCompaniesException;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserTest {
//  static List<AbstractMap.SimpleEntry<Company, Double>> expectationList;
//
//  @BeforeAll
//  public static void initMap() {
//	expectationList = new ArrayList<>();
//	expectationList.add(new AbstractMap.SimpleEntry<>(new Company("AAPL", "NAS"), 12.33));
//	expectationList.add(new AbstractMap.SimpleEntry<>(new Company("YNDX", "NAS"), 15.66));
//	expectationList.add(new AbstractMap.SimpleEntry<>(new Company("SBR", "NAS"), 132.80));
//  }
//
//  @Test
//  public void setAddCompany_addFourCompaniesToWatchList_TooManyCompaniesException() {
//	User user = new User();
//	for (AbstractMap.SimpleEntry<Company, Double> companyDoubleEntry : expectationList) {
//	  user.addCompanyToWatchList(companyDoubleEntry.getKey(), companyDoubleEntry.getValue());
//	}
//	assertThrows(TooManyCompaniesException.class, () -> user.addCompanyToWatchList(new Company(), 1));
//	assertEquals(3, user.getCurSubscribes());
//	assertEquals(3, user.getCompanies().size());
//  }
//
//  @Test
//  public void deleteCompanyFromWatchList_deleteOneCompany() {
//	User user = new User(1);
//	for (AbstractMap.SimpleEntry<Company, Double> companyDoubleEntry : expectationList) {
//	  user.addCompanyToWatchList(companyDoubleEntry.getKey(), companyDoubleEntry.getValue());
//	}
//	Company company = new Company("AAPL", "NAS");
//
//	user.deleteCompanyFromWatchList(company);
//	assertEquals(2, user.getCompanies().size());
//  }
//
//  @Test
//  public void deleteCompanyFromWatchList_deleteCompanyWhichNotInList_NoSuchElementException() {
//	User user = new User(1);
//	for (AbstractMap.SimpleEntry<Company, Double> companyDoubleEntry : expectationList) {
//	  user.addCompanyToWatchList(companyDoubleEntry.getKey(), companyDoubleEntry.getValue());
//	}
//
//	assertThrows(NoSuchElementException.class, () ->
//			user.deleteCompanyFromWatchList(new Company("I", "NAS")));
//  }
//
//  @TestFactory
//  public Collection<DynamicTest> isAbleToAddCompany_testFactory() {
//	return Arrays.asList(
//			DynamicTest.dynamicTest("is able to add company to new user",
//					() -> assertTrue(new User(1).isAbleToAddCompany())),
//			DynamicTest.dynamicTest("is able to add second company", () -> {
//			  User user = new User(1);
//			  user.addCompanyToWatchList(expectationList.get(0).getKey(), expectationList.get(0).getValue());
//			  assertEquals(user.getCurSubscribes(), 1);
//			  assertTrue(user.isAbleToAddCompany());
//			}),
//			DynamicTest.dynamicTest("is able to add last company", () -> {
//			  User user = new User(1);
//			  user.addCompanyToWatchList(expectationList.get(0).getKey(), expectationList.get(0).getValue());
//			  user.addCompanyToWatchList(expectationList.get(1).getKey(), expectationList.get(1).getValue());
//			  assertEquals(user.getCurSubscribes(), 2);
//			  assertTrue(user.isAbleToAddCompany());
//			}),
//			DynamicTest.dynamicTest("is able to add 4th company (expected false)", () -> {
//			  User user = new User(1);
//			  for (AbstractMap.SimpleEntry<Company, Double> companyDoubleEntry : expectationList) {
//				user.addCompanyToWatchList(companyDoubleEntry.getKey(), companyDoubleEntry.getValue());
//			  }
//			  assertFalse(user.isAbleToAddCompany());
//			}));
//  }
//}