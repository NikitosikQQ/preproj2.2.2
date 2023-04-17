package com.example.demo.controller;

import com.example.demo.car.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public void getListOfCars(@Value("${spring.field.maxCars}") Long maxCars,
                              @RequestParam(value = "count", required = false) Long count,
                              Model model) {
        model.addAttribute("cars", carService.getListOfCarsByCount(count, maxCars));
    }
}
