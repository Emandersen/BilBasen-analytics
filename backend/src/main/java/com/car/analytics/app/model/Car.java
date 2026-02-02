package com.car.analytics.app.model;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "car_model_id")
    private CarModel carModel;

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