package com.juanjo.user_service.feignClients;

import com.juanjo.user_service.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bike-service", url = "http://localhost:8003", path = "/bike")
public interface BikeFeignClient {

    @PostMapping
    Bike save(@RequestBody Bike bike);

    @GetMapping("/getAllBikesByUser/{userId}")
    List<Bike> getAllBikesByUserId(@PathVariable("userId") int userId);
}
