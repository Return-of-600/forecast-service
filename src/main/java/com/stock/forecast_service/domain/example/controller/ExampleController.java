package com.stock.forecast_service.domain.example.controller;

import com.stock.forecast_service.common.api.ApiResponse;
import com.stock.forecast_service.common.exception.CustomException;
import com.stock.forecast_service.common.exception.code.UserErrorCode;
import com.stock.forecast_service.domain.example.entity.Example;
import com.stock.forecast_service.domain.example.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

  @GetMapping("/api/ok")
  public ApiResponse<?> checkApiResponse() {
    HashMap<String ,String> map = new HashMap<>();
    map.put("name", "Hong");
    map.put("address", "Ulsan");

    return ApiResponse.ok(map);
  }

  @GetMapping("/api/created")
  public ApiResponse<?> checkApiResponseCreate() {
    HashMap<String ,String> map = new HashMap<>();
    map.put("name", "Hong Created");
    map.put("address", "Ulsan");

    return ApiResponse.created(map);
  }

  @GetMapping("/api/fail")
  public ApiResponse<?> checkApiResponseFail() {
    throw new CustomException(UserErrorCode.USER_EXPIRED);
  }

}
