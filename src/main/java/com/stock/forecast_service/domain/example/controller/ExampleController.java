package com.stock.forecast_service.domain.example.controller;

import com.stock.forecast_service.domain.example.dto.ExampleDto;
import com.stock.forecast_service.domain.example.entity.Example;
import com.stock.forecast_service.domain.example.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/example")
@RestController
public class ExampleController {

  private final ExampleService service;

  @GetMapping("/jpa")
  public List<Example> findByNameJpa(@RequestParam String name) {
    return service.findByNameByJpa(name);
  }

  @GetMapping("/mybatis")
  public List<Example> findByNameMybatis(@RequestParam String name) {
    return service.findByNameMyBatis(name);
  }


}
