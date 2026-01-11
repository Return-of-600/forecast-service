package com.stock.forecast_service.common.exception.code;

import com.stock.forecast_service.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

  NO_PERMISSION(HttpStatus.FORBIDDEN, "Permission denied."),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found."),
  USER_EXPIRED(HttpStatus.UNAUTHORIZED, "User account expired.");

  private final HttpStatus httpStatus;
  private final String message;

}
