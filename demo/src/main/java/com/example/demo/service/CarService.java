package com.example.demo.service;

import com.example.demo.car.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getListOfCars() {
        return carRepository.findAll();
    }

    public List<Car> getListOfCarsByCount(Long count, Long maxCars) {
        if (count == null || count >= maxCars) {
            return getListOfCars();
        } else {
            return carRepository.getListOfCarsByCount(count);
        }
    }
}
