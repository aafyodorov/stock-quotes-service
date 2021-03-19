package tk.stockquotesservice.exception;

import feign.Response;

/**
 * @author Andrey Fyodorov
 * Created on 18.03.2021
 */

public class GenericFeignException extends RuntimeException{
  private final Response response;

  public GenericFeignException(Response response) {
    this.response = response;
  }

  public Response getResponse() {
	return response;
  }
}
