package com.car.analytics.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Car")
@Getter
@Setter
public class Car {

    @Id
    @Column(name = "car_external_id")
    private Long externalId;


    private String make;
    private String model;
    private String variant;

    private int year;
    private String firstRegistration;
    private int mileage;

    private String engineType; 

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_performance_id", referencedColumnName = "id")
    private CarPerformance carPerformance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_economics_id", referencedColumnName = "id")
    private CarEconomics carEconomics;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceHistory> priceHistories = new ArrayList<>();
}