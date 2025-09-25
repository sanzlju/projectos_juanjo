package com.juanjo.bike_service.controller;

import com.juanjo.bike_service.entity.Bike;
import com.juanjo.bike_service.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> Bikes = bikeService.getAll();
        if(Bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable("id") int id) {
        Bike Bike = bikeService.getBikeById(id);
        if(Bike == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Bike);
    }

    @PostMapping
    public ResponseEntity<Bike> saveBike(@RequestBody Bike Bike) {
        Bike BikeNew = bikeService.save(Bike);
        return ResponseEntity.ok(BikeNew);
    }


    @GetMapping("/getAllBikesByUser/{userId}")
    public ResponseEntity<List<Bike>> getAllBikesByUserId(@PathVariable("userId") int userId) {
        List<Bike> Bikes = bikeService.findBikesByUserId(userId);
        /*if(Bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }*/
        return ResponseEntity.ok(Bikes);
    }
}
