package test.zapptsmagicapi.exceptions;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
