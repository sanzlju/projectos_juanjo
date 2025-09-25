package com.juanjo.bike_service.repository;

import com.juanjo.bike_service.entity.Bike;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends ListCrudRepository<Bike,Integer> {

    List<Bike> findByUserId(int userId);
}
