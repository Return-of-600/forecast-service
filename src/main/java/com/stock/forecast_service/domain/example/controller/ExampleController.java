package com.stock.forecast_service.domain.example.controller;

import com.stock.forecast_service.common.exception.code.CommonErrorCode;
import com.stock.forecast_service.common.exception.CoreException;
import com.stock.forecast_service.common.exception.code.UserErrorCode;
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

  @GetMapping("/ex")
  public void checkException() {
    throw new IllegalArgumentException("예상치 못한 예외");
    // throw new CoreException(UserErrorCode.USER_NOT_FOUND);
  }

}
