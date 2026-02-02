package com.car.analytics.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.analytics.app.model.CarPriceHistory;

public interface CarPriceHistoryRepository extends JpaRepository<CarPriceHistory, Long> {
    
}
