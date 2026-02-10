package com.stock.forecast_service.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member {

  @Id
  private String memberId;

  @Column(nullable = false)
  private String roleId;

  @Column(nullable = false)
  private String memberPw;

  @Column(nullable = false)
  private String name;

  private String email;

  private String phone;

  private String stat;

  @CreatedDate
  private LocalDateTime createDt;

  @LastModifiedDate
  private LocalDateTime updateDt;

}
