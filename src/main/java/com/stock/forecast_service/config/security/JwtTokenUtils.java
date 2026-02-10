package com.stock.forecast_service.config.security;

import com.stock.forecast_service.domain.member.dto.MemberDto;
import com.stock.forecast_service.domain.member.entity.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
public class JwtTokenUtils {

  private static SecretKey JWT_SECRET_KEY;

  public JwtTokenUtils(@Value("${jwt.secret-key}") String jwtSecretKey) {
    JwtTokenUtils.JWT_SECRET_KEY = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
  }

  /* JWT Token 생성 */
  public static String generateJwt(Member member, int maxIdleHour) {
    return Jwts.builder()
        .setHeader(createHeader())
        .claims(createClaims(member))
        .subject(member.getMemberId())
        .signWith(JWT_SECRET_KEY)
        .expiration(createExpiredDate(maxIdleHour))
        .compact();
  }

  private static Date createExpiredDate(int maxIdleHour) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.HOUR, maxIdleHour);

    return calendar.getTime();
  }

  private static Map<String, Object> createHeader() {
    return Jwts.header()
        .add("typ", "JWT")
        .add("alg", "HS256")
        .add("reg", System.currentTimeMillis())
        .build();
  }

  private static Map<String, Object> createClaims(Member member) {
    Map<String, Object> claimsMap = new HashMap<>();

    log.info("memberId : {0}", member.getMemberId());
    log.info("email : {0}", member.getEmail());

    claimsMap.put("memberId", member.getMemberId());
    claimsMap.put("email", member.getEmail());

    return claimsMap;
  }

  /* Refresh Token */
  public static String generateRefreshToken(Member member, int refreshTokenIdleDay) {
    log.debug("JWT Secret Key: " + JWT_SECRET_KEY);

    return Jwts.builder()
        .setHeader(createHeader())
        .claims(createClaims(member))
        .subject(member.getMemberId())
        .signWith(JWT_SECRET_KEY)
        .expiration(createRefreshTokenExpiredDate(refreshTokenIdleDay))
        .compact();
  }

  private static Date createRefreshTokenExpiredDate(int refreshTokenIdleDay) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, refreshTokenIdleDay);
    return calendar.getTime();
  }



}
