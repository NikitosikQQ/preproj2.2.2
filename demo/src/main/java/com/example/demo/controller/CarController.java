package com.example.demo.controller;

import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

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
                                           @RequestParam(value = "filterBy", required = false) String filter,
                                           Model model) {
        if (filter == null) {
            model.addAttribute("cars", carService.getListOfCarsByCount(count));
        } else {
            if(filter.equalsIgnoreCase("id") && !carService.isSortById()){
                throw new BadRequestException();
            }
            if(filter.equalsIgnoreCase("model") && !carService.isSortByModel()){
                throw new BadRequestException();
            }
            if(filter.equalsIgnoreCase("type") && !carService.isSortByType()){
                throw new BadRequestException();
            }
            if(filter.equalsIgnoreCase("series") && !carService.isSortBySeries()){
                throw new BadRequestException();
            } else {
                model.addAttribute("cars", carService.getListOfCarsSorted(count, filter));
            }
        }
    }
}
