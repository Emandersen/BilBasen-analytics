package com.car.analytics.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.analytics.app.model.CarLocation;

public interface CarLocationRepository extends JpaRepository<CarLocation, Long> {
    
}
