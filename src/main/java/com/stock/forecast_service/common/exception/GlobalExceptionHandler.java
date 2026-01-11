package com.stock.forecast_service.common.exception;

import com.stock.forecast_service.common.exception.code.CommonErrorCode;
import com.stock.forecast_service.common.exception.CoreException;
import com.stock.forecast_service.common.exception.ErrorCode;
import com.stock.forecast_service.common.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CoreException.class)
  public ResponseEntity<?> handleCustomException(CoreException e) {
    ErrorCode errorCode = e.getErrorCode();

    return ResponseEntity.status(errorCode.getHttpStatus())
        .body(makeErrorResponse(errorCode));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleException(Exception e) {
    log.warn("Unhandled exception ", e);

    ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
    return ResponseEntity.status(errorCode.getHttpStatus())
        .body(makeErrorResponse(errorCode));
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
    return handleExceptionInternal(ex, errorCode);
  }

  private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorCode errorCode) {
    log.warn("handleExceptionInternal", e);

    return ResponseEntity.status(errorCode.getHttpStatus())
        .body(makeErrorResponse(errorCode, e.getMessage()));
  }

  private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
    return ErrorResponse.builder()
        .code(errorCode.name())
        .message(errorCode.getMessage())
        .build();
  }

  private ErrorResponse makeErrorResponse(ErrorCode errorCode, String message) {
    return ErrorResponse.builder()
        .code(errorCode.name())
        .message(message)
        .build();
  }

}
