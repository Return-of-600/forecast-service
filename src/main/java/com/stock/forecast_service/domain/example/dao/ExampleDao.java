package com.stock.forecast_service.domain.example.dao;

import com.stock.forecast_service.domain.example.entity.Example;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExampleDao {

  List<Example> findByName(@Param("name") String name);

}
