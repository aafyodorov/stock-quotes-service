package tk.stockquotesservice.exception.handlers;

import feign.FeignException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tk.stockquotesservice.exception.GenericFeignException;

import javax.servlet.http.HttpServletResponse;
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
  public ResponseEntity<JSONObject> handleFeignClientExceptions(FeignException ex, WebRequest request) {
    Pattern messagePattern = Pattern.compile("\\[(.*)] during \\[(.*)] to \\[(.*)] \\[(.*)]: \\[(.*)]");
    Matcher messageMatcher = messagePattern.matcher(ex.getMessage());
    JSONObject responseBody = new JSONObject();
    if (messageMatcher.find()) {
    } else {
      logger.error("In {}.class ", RestResponseEntityExceptionHandler.class.getName());
//      responseBody.put("message", "")
      throw ex;
    }
    return null;
  }
}
