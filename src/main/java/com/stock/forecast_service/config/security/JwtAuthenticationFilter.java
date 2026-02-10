package com.stock.forecast_service.config.security;

import org.springframework.context.annotation.Configuration;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

@Configuration
public class JwtAuthenticationFilter implements Filter {


  @Override
  public boolean isLoggable(LogRecord record) {
    return false;
  }
}
