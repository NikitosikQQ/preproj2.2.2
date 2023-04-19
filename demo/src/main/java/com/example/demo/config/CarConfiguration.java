package com.example.demo.config;

import com.example.demo.car.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CarConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(CarRepository carRepository) {
        return args -> {
            Car car1 = new Car("BMW123", "Sedan", 1);
            Car car2 = new Car("Mercedes4", "Sedan", 121);
            Car car3 = new Car("Zhigul", "Hatch—back", 100);
            Car car4 = new Car("Audi", "Minivan", 33);
            Car car5 = new Car("Mercedes4", "Sedan", 121);
            Car car6 = new Car("Mercedes", "Sedan", 121);
            Car car7 = new Car("BMW", "Sedan", 121);
            Car car8 = new Car("Nissan", "Minivan", 121);
            Car car9 = new Car("Zhigul", "Sedan", 121);
            Car car10 = new Car("Audi", "Hatch—back", 121);
            carRepository.saveAll(List.of(car1, car2, car3, car4, car5, car6, car7, car8, car9, car10));
        };
    }
}
