package tk.stockquotesservice.exception;

/**
 * @author Andrey Fyodorov
 * Created on 15.03.2021
 */

public class TooManyCompaniesException extends RuntimeException {
  public TooManyCompaniesException() {
	super();
  }

  public TooManyCompaniesException(String message) {
	super(message);
  }
}
