package tk.stockquotesservice.exception.handlers;

import feign.FeignException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Andrey Fyodorov
 * Created on 18.03.2021
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

  @ExceptionHandler(value = {FeignException.class})
  public ResponseEntity<Object> handleFeignClientExceptions(FeignException ex, WebRequest request) {
	Pattern messagePattern = Pattern.compile("\\[(.*)] during \\[(.*)] to \\[(.*)] \\[(.*)]: \\[(.*)]");
	Matcher messageMatcher = messagePattern.matcher(ex.getMessage());
	JSONObject responseBody = new JSONObject();
	HttpHeaders headers = new HttpHeaders();
	int status;
	String statusStr;

	headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	if (messageMatcher.find()) {
	  int firstSpace = messageMatcher.group(1).indexOf(' ');
	  if (firstSpace != -1) {
		statusStr = messageMatcher.group(1).substring(0, firstSpace);
		responseBody.put("reason", messageMatcher.group(1).substring(firstSpace + 1));
	  } else {
		statusStr = messageMatcher.group(1);
	  }
	  status = Integer.parseInt(statusStr);
	  responseBody.put("status", status);
	  responseBody.put("message", messageMatcher.group(5));
	} else {
	  logger.error("In [{}.class] Feign message pattern doesn't match the exception message.",
			  RestResponseEntityExceptionHandler.class.getName());
	  responseBody.put("status", 500);
	  responseBody.put("message", "Error when requesting IEXCloud.");
	  status = 500;
	}
	return handleExceptionInternal(ex, responseBody.toString(), headers, HttpStatus.valueOf(status), request);
  }
}
