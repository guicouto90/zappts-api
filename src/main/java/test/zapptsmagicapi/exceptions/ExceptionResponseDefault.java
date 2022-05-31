package test.zapptsmagicapi.exceptions;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class ExceptionResponseDefault implements Serializable {

  private static final long serialVersionUID = 1L;
  private Date timestamp;
  private String message;
  private Map<String, String> errors;
  private String details;

  public ExceptionResponseDefault(Date timestamp, String message, String details) {
    super();
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }

  public ExceptionResponseDefault(Date timestamp, Map<String, String> errors, String details) {
    super();
    this.timestamp = timestamp;
    this.message = "These fields below are filled incorrect: ";
    this.errors = errors;
    this.details = details;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }


  public Map<String,String> getErrors() {
    return this.errors;
  }

  public void setErrors(Map<String,String> errors) {
    this.errors = errors;
  }


}
