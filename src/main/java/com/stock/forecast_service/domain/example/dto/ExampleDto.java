package com.stock.forecast_service.domain.example.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExampleDto {

  private Long id;

  private String name;

  private LocalDateTime createDate;

}
