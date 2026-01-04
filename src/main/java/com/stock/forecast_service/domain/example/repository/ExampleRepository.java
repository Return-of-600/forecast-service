package com.stock.forecast_service.domain.example.repository;

import com.stock.forecast_service.domain.example.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExampleRepository extends JpaRepository<Example, Long> {

  List<Example> findByName(String name);

}
