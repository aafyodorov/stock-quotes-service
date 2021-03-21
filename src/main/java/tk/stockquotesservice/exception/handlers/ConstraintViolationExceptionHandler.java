package tk.stockquotesservice.exception.handlers;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author Andrey Fyodorov
 * Created on 21.03.2021
 */

@ControllerAdvice
public class ConstraintViolationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ConstraintViolationException.class})
  public ResponseEntity<Object> handle(ConstraintViolationException constraintViolationException) {
    JSONObject violationsJSON = new JSONObject();
    JSONObject body = new JSONObject();
    Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();

    if (!violations.isEmpty()) {
      violations.forEach(violation -> violationsJSON.put(String.valueOf(violation.getPropertyPath()), violation.getMessage()));
    } else {
      violationsJSON.put("unknownViolation", "ConstraintViolationException occurred");
    }
    body.put("ConstraintViolations", violationsJSON);
    return new ResponseEntity<>(body.toString(), HttpStatus.BAD_REQUEST);
  }
}
