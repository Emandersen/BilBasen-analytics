package com.car.analytics.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.analytics.app.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
    
}