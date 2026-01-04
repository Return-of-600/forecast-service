package com.stock.forecast_service.domain.example.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "example")
@Getter
@NoArgsConstructor
public class Example {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Nonnull
  private String name;

  @Column(name = "create_date")
  private LocalDateTime createDate;

}
