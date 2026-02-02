package com.car.analytics.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.analytics.app.model.CarEconomics;

public interface CarEconomicsRepository extends JpaRepository<CarEconomics, Long> {

}
