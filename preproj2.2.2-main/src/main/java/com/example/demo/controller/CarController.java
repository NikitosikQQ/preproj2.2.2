package com.example.demo.controller;

import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public void getListOfCars(@RequestParam(value = "count", required = false) Long count,
                                           @RequestParam(value = "sortBy", required = false) String sort,
                                           Model model) {
        if (sort == null) {
            model.addAttribute("cars", carService.getListOfCarsByCount(count));
        } else {
                model.addAttribute("cars", carService.getListOfCarsSorted(count, sort));
        }
    }
}
