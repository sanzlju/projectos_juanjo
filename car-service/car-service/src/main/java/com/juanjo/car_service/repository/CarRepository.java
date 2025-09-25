package com.juanjo.car_service.repository;

import com.juanjo.car_service.entity.Car;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends ListCrudRepository<Car,Integer> {

    List<Car> findByUserId(int userId);
}
