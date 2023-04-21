package com.example.demo.service;

import com.example.demo.car.Car;
import com.example.demo.controller.BadRequestException;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "car")
public class CarService {
    private boolean sortById;
    private boolean sortByModel;
    private boolean sortByType;
    private boolean sortBySeries;
    private Long maxCars;
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getListOfCars() {
        return carRepository.findAll();
    }

    public List<Car> getListOfCarsByCount(Long count) {
        if (count == null || count >= maxCars) {
            return getListOfCars();
        } else {
            return carRepository.getListOfCarsByCount(count);
        }
    }

    public List<Car> getListOfCarsSorted(Long count, String sort) {
        if (count == null || count >= maxCars) {
            count = maxCars;
        }
        if (sort.equalsIgnoreCase("id") && !isSortById()) {
            throw new BadRequestException();
        }
        if (sort.equalsIgnoreCase("model") && !isSortByModel()) {
            throw new BadRequestException();
        }
        if (sort.equalsIgnoreCase("type") && !isSortByType()) {
            throw new BadRequestException();
        }

        if (sort.equalsIgnoreCase("series") && !isSortBySeries()) {
            throw new BadRequestException();
        } else {
            return carRepository.getListOfCarsFiltered(count, sort);
        }

    }

    public boolean isSortById() {
        return sortById;
    }

    public void setSortById(boolean sortById) {
        this.sortById = sortById;
    }

    public boolean isSortByModel() {
        return sortByModel;
    }

    public void setSortByModel(boolean sortByModel) {
        this.sortByModel = sortByModel;
    }

    public boolean isSortByType() {
        return sortByType;
    }

    public void setSortByType(boolean sortByType) {
        this.sortByType = sortByType;
    }

    public boolean isSortBySeries() {
        return sortBySeries;
    }

    public void setSortBySeries(boolean sortBySeries) {
        this.sortBySeries = sortBySeries;
    }

    public Long getMaxCars() {
        return maxCars;
    }

    public void setMaxCars(Long maxCars) {
        this.maxCars = maxCars;
    }
}
