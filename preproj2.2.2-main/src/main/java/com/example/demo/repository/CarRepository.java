package com.example.demo.repository;

import com.example.demo.car.Car;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Car LIMIT :count")
    List<Car> getListOfCarsByCount (@Param("count") Long count);

    @Query(nativeQuery = true, value = "SELECT * FROM Car Order By " +
            "CASE WHEN :sort = 'id'  THEN Car.id END ASC, " +
            "CASE WHEN :sort = 'model'  THEN Car.model END ASC," +
            "CASE WHEN :sort = 'type'  THEN Car.type END ASC," +
            "CASE WHEN :sort = 'series'  THEN Car.series END ASC" +
            " LIMIT :count")

    List<Car> getListOfCarsFiltered (@Param("count") Long count, @Param("sort") String sort);
}
