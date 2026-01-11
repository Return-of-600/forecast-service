package com.stock.forecast_service.common.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stock.forecast_service.common.exception.CustomException;
import com.stock.forecast_service.common.exception.ErrorCode;
import com.stock.forecast_service.common.exception.ErrorResponse;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
    @JsonIgnore HttpStatus httpStatus,
    boolean success,
    @Nullable T data,
    @Nullable ErrorResponse error) {

  public static <T> ApiResponse<T> ok(@Nullable final T data) {
    return new ApiResponse<>(HttpStatus.OK, true, data, null);
  }

  public static <T> ApiResponse<T> created(@Nullable final T data) {
    return new ApiResponse<>(HttpStatus.CREATED, true, data, null);
  }

  public static <T> ApiResponse<T> fail(final CustomException ex) {
    ErrorCode errorCode = ex.getErrorCode();

    return new ApiResponse<>(
        errorCode.getHttpStatus(),
        false,
        null,
        ErrorResponse.builder()
          .code(errorCode.name())
          .message(errorCode.getMessage())
          .build());
  }

  public static <T> ApiResponse<T> fail(final ErrorCode errorCode) {
    return new ApiResponse<>(
        errorCode.getHttpStatus(),
        false,
        null,
        ErrorResponse.builder()
            .code(errorCode.name())
            .message(errorCode.getMessage())
            .build());
  }

}
