package test.zapptsmagicapi.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ExceptionHandlerController {

  /* @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponseDefault> handleInternalErrorException(WebRequest web) {
    ExceptionResponseDefault response =
        new ExceptionResponseDefault(new Date(), "Internal Server Error", web.getDescription(false));
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  } */

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ExceptionResponseDefault> handleNoSuchElementFound(ExceptionNotFound exception, WebRequest web) {
    ExceptionResponseDefault response =
        new ExceptionResponseDefault(new Date(), exception.getMessage(), web.getDescription(false));
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(ExceptionNotFound.class)
  public ResponseEntity<ExceptionResponseDefault> handleNotFound(ExceptionNotFound exception, WebRequest web) {
    ExceptionResponseDefault response =
        new ExceptionResponseDefault(new Date(), exception.getMessage(), web.getDescription(false));
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ExceptionResponseDefault> handleIllegalArgument(IllegalArgumentException exception, WebRequest web) {
    ExceptionResponseDefault response =
        new ExceptionResponseDefault(new Date(), exception.getMessage(), web.getDescription(false));
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  // REF: https://www.tabnine.com/code/java/methods/org.springframework.web.bind.MethodArgumentNotValidException/getMessage
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponseDefault> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest web) {
    Map<String, String> errorList = new HashMap<>();
    exception.getBindingResult().getAllErrors()
      .stream()
      .filter(FieldError.class::isInstance)
      .map(FieldError.class::cast)
      .forEach(fieldError -> errorList.put(fieldError.getField(), fieldError.getDefaultMessage()));
    ExceptionResponseDefault response =
        new ExceptionResponseDefault(new Date(), errorList, web.getDescription(false));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  //REF: https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception, WebRequest web) {
    Map<String, String> errorList = new HashMap<>();
    for(ConstraintViolation<?> violation : exception.getConstraintViolations()) {
      errorList.put(violation.getPropertyPath().toString(), violation.getMessage());
    }
    ExceptionResponseDefault response =
        new ExceptionResponseDefault(new Date(), errorList, web.getDescription(false));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(ExceptionBadRequest.class)
  public ResponseEntity<ExceptionResponseDefault> handleLanguageException(ExceptionBadRequest exception, WebRequest web) {
    ExceptionResponseDefault response =
        new ExceptionResponseDefault(new Date(), exception.getMessage(), web.getDescription(false));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

}
