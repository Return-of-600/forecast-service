package com.stock.forecast_service.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (request.getParameterNames().hasMoreElements()) {
      log.info("Request Method: [{}] URL: [{}] Params: [{}]",request.getMethod(), request.getRequestURI(), getRequestParams(request));
    } else {
      log.info("Request Method: [{}] URL: [{}]",request.getMethod(), request.getRequestURI());
    }

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    log.info("Response Status : [{}]", response.getStatus());
  }

  // Request Param 로깅을 위해 사용.
  private Map<String, String> getRequestParams(HttpServletRequest request) {
    Map<String, String> paramMap = new HashMap<>();
    Enumeration<String> parameterNames = request.getParameterNames();

    while (parameterNames.hasMoreElements()) {
      String paramName = parameterNames.nextElement();
      paramMap.put(paramName, request.getParameter(paramName));
    }

    return paramMap;
  }


}
