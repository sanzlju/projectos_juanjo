package com.juanjo.user_service.service;

import com.juanjo.user_service.entity.User;
import com.juanjo.user_service.feignClients.BikeFeignClient;
import com.juanjo.user_service.feignClients.CarFeignClient;
import com.juanjo.user_service.model.Bike;
import com.juanjo.user_service.model.Car;
import com.juanjo.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableFeignClients
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;
    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById (int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save (User user) {
        User userNew = userRepository.save(user);
        return userNew;
    }

    public List<Car> getCars(int userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/getAllCarsByUser/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/getAllBikesByUser/" + userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike (int userId, Bike bike){
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String,Object> getUserAndVehicules(int userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            result.put("message:", "user does not exist");
            return result;
        }
        result.put("User:" , user);
        List<Car> cars = carFeignClient.getAllCarsByUserId(userId);
        if (cars.isEmpty()){
            result.put("Cars:", "this user does not have cars");
        }
        else
            result.put("Cars:", cars);
        List<Bike> bikes = bikeFeignClient.getAllBikesByUserId(userId);
        if (bikes.isEmpty()){
            result.put("Bikes:", "this user does not have bikes");
        }
        else
            result.put("Bikes:", bikes);
        return result;
    }

}
