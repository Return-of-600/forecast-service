package com.stock.forecast_service.domain.example.service;

import com.stock.forecast_service.domain.example.dao.ExampleDao;
import com.stock.forecast_service.domain.example.entity.Example;
import com.stock.forecast_service.domain.example.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService {

  private final ExampleDao exampleDao;
  private final ExampleRepository exampleRepository;

  public List<Example> findByNameByJpa(String name) {
    return exampleRepository.findByName(name);
  }

  public List<Example> findByNameMyBatis(String name) {
    return exampleDao.findByName(name);
  }

}
