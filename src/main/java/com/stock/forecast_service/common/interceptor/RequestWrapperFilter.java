package com.stock.forecast_service.common.interceptor;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class RequestWrapperFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    if (servletRequest instanceof HttpServletRequest) {
      HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

      CustomHttpRequestWrapper requestWrapper = new CustomHttpRequestWrapper(httpRequest);

      filterChain.doFilter(requestWrapper, servletResponse);
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }
}
