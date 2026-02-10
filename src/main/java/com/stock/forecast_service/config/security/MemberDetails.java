package com.stock.forecast_service.config.security;

import com.stock.forecast_service.domain.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

public class MemberDetails implements UserDetails {

  private final Member member;
  private final int maxIdleDays;
  private final String activeStatusCode;

  public MemberDetails(Member member, int maxIdleDays, String activeStatusCode) {
    this.member = member;
    this.maxIdleDays = maxIdleDays;
    this.activeStatusCode = activeStatusCode;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getPassword() {
    return member.getMemberPw();
  }

  @Override
  public String getUsername() {
    return member.getMemberId();
  }

  @Override
  public boolean isAccountNonExpired() {
    LocalDateTime currentDate = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    LocalDateTime userInfoUpdated = member.getUpdateDt();

    return ChronoUnit.DAYS.between(currentDate, userInfoUpdated) < maxIdleDays;
  }

  @Override
  public boolean isAccountNonLocked() {
    return member.getStat().equals(activeStatusCode);
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }
}
