package nl.strohalm.cyclos.mfs.exceptions;

import org.springframework.http.HttpStatus;

public class MFSCommonException extends RuntimeException {

  HttpStatus httpStatus;
  String errorCode;

  public MFSCommonException(String errorCode, String message,HttpStatus httpStatus) {
    super(message);
    this.errorCode = errorCode;
    this.httpStatus=httpStatus;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
}
