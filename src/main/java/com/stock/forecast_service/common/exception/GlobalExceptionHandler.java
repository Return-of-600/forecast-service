package com.stock.forecast_service.common.exception;

import com.stock.forecast_service.common.api.ApiResponse;
import com.stock.forecast_service.common.exception.code.CommonErrorCode;
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

  @ExceptionHandler(CustomException.class)
  public ApiResponse<?> handleCustomException(CustomException e) {
    return ApiResponse.fail(e);
  }

  @ExceptionHandler(Exception.class)
  public ApiResponse<?> handleException(Exception e) {
    log.error("Unhandled exception ", e);

    return ApiResponse.fail(CommonErrorCode.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
    return ResponseEntity
        .status(errorCode.getHttpStatus())
        .body(makeErrorResponse(errorCode, ex.getMessage()));
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
