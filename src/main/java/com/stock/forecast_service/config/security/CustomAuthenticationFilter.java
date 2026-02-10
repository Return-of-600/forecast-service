package com.stock.forecast_service.config.security;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.forecast_service.domain.member.dto.MemberDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

// @Configuration
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
    super.setAuthenticationManager(authenticationManager);
  }


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    UsernamePasswordAuthenticationToken authRequest;

    try {
      authRequest = getAuthRequest(request);

      setDetails(request, authRequest);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return this.getAuthenticationManager().authenticate(authRequest);
  }

  private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) throws IOException {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

      MemberDto member = objectMapper.readValue(request.getInputStream(), MemberDto.class);

      return new UsernamePasswordAuthenticationToken(member.getMemberId(), member.getPassword());

    } catch (UsernameNotFoundException ex) {
      throw new UsernameNotFoundException(ex.getMessage());
    }
  }


}
