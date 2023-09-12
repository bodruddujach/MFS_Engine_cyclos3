package nl.strohalm.cyclos.mfs.exceptions;

import nl.strohalm.cyclos.services.access.exceptions.InvalidCredentialsException;
import nl.strohalm.cyclos.services.transactions.exceptions.NotEnoughCreditsException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
  public static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);


  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleUnknownException(final Exception ex, final HttpServletResponse response) throws IOException {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode("5001");
    errorResponse.setMessage(ex.getMessage());
    logger.error("Exception",ex);
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

  }


  @ExceptionHandler(NotEnoughCreditsException.class)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleNotEnoughCreditsException(final NotEnoughCreditsException ex){
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode("4000");
    errorResponse.setMessage("Insufficient Balance");
    logger.error("Exception",ex);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MFSLimitException.class)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleMfsLimitException(final MFSLimitException ex){
      ErrorResponse errorResponse = new ErrorResponse();
      errorResponse.setCode(ex.getErrorCode());
      errorResponse.setMessage(ex.getMessage());
      logger.error("Exception", ex);
      return new ResponseEntity<>(errorResponse, ex.httpStatus);
  }

  @ExceptionHandler(MFSCommonException.class)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleMFSCommonException(final MFSCommonException ex){
      ErrorResponse errorResponse = new ErrorResponse();
      errorResponse.setCode(ex.getErrorCode());
      errorResponse.setMessage(ex.getMessage());
      logger.error("Exception",ex);
      return new ResponseEntity<>(errorResponse, ex.httpStatus);
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(InvalidCredentialsException.class)
  @ResponseBody
  public ErrorResponse handleInvalidCredentialsException(InvalidCredentialsException e){
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(e.getMessage());
    errorResponse.setCode(e.getMessage());
    return errorResponse;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException ex) {
      BindingResult result = ex.getBindingResult();
      List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
      return processFieldErrors(fieldErrors);
  }

  private ErrorResponse processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
      ErrorResponse errorResponse = new ErrorResponse();
      errorResponse.setCode("4000");
      errorResponse.setMessage("Invalid Input");
      for (org.springframework.validation.FieldError fieldError: fieldErrors) {
          errorResponse.setMessage(fieldError.getDefaultMessage());
      }
      return errorResponse;
  }

}