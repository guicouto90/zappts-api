package test.zapptsmagicapi.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ExceptionHandlerController {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponseDefault> handleInternalErrorException(WebRequest web) {
    ExceptionResponseDefault response =
        new ExceptionResponseDefault(new Date(), "Internal Server Error", web.getDescription(false));
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

}
