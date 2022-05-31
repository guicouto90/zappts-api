package test.zapptsmagicapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LanguageException extends RuntimeException {

  public LanguageException(String message) {
    super(message);
  }
  
}
