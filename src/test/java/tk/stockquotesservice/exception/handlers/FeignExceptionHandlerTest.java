package tk.stockquotesservice.exception.handlers;

import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FeignExceptionHandlerTest {

  @Test
  public void handleFeignClientExceptions_FeignPatternNotMatch() {
	FeignException ex = Mockito.mock(FeignException.class);
	WebRequest request = Mockito.mock(WebRequest.class);
	when(ex.getMessage()).thenReturn("Message not matching pattern");
	FeignExceptionHandler entityExceptionHandler = new FeignExceptionHandler();

	ResponseEntity<Object> responseEntity = entityExceptionHandler.handleFeignClientExceptions(ex, request);
	assertEquals(responseEntity.getStatusCodeValue(), 500);
	String body = (String) responseEntity.getBody();
	assertNotNull(body);
	assertTrue(body.contains("Error when requesting IEXCloud."));
	assertTrue(body.contains("message"));
  }

  @Test
  public void handleFeignClientExceptions_CorrectPatternWithReason() {
	FeignException ex = Mockito.mock(FeignException.class);
	WebRequest request = Mockito.mock(WebRequest.class);
	FeignExceptionHandler entityExceptionHandler = new FeignExceptionHandler();

	when(ex.getMessage())
			.thenReturn("[404 Not Found] during [GET] to [https://www.correct-uri] " +
					"[IEXClient#getCompany(String,String)]: [Unknown symbol]");
	ResponseEntity<Object> responseEntity = entityExceptionHandler.handleFeignClientExceptions(ex, request);
	assertEquals(responseEntity.getStatusCodeValue(), 404);
	String body = (String) responseEntity.getBody();
	assertNotNull(body);
	assertTrue(body.contains("status"));
	assertTrue(body.contains("message"));
	assertTrue(body.contains("\"reason\":\"Not Found\""));
  }

  @Test
  public void handleFeignClientExceptions_CorrectPatternWithoutReason() {
	FeignException ex = Mockito.mock(FeignException.class);
	WebRequest request = Mockito.mock(WebRequest.class);
	FeignExceptionHandler entityExceptionHandler = new FeignExceptionHandler();

	when(ex.getMessage())
			.thenReturn("[404] during [GET] to [https://www.correct-uri] " +
					"[IEXClient#getCompany(String,String)]: [Unknown symbol]");
	ResponseEntity<Object> responseEntity = entityExceptionHandler.handleFeignClientExceptions(ex, request);
	assertEquals(responseEntity.getStatusCodeValue(), 404);
	String body = (String) responseEntity.getBody();
	assertNotNull(body);
	assertTrue(body.contains("status"));
	assertFalse(body.contains("reason"));
	assertTrue(body.contains("message"));
  }
}