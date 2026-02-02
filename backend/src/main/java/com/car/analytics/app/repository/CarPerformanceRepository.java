package com.car.analytics.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.analytics.app.model.CarPerformance;

public interface CarPerformanceRepository extends JpaRepository<CarPerformance, Long> {
    
}
