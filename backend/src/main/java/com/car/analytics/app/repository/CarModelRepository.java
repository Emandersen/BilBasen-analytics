package com.car.analytics.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.analytics.app.model.CarModel;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    
}
