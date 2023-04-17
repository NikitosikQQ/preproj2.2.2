package com.example.demo.repository;

import com.example.demo.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Car LIMIT :count")
    List<Car> getListOfCarsByCount (@Param("count") Long count);

}
