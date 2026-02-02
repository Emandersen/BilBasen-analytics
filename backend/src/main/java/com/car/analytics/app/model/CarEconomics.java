package com.car.analytics.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CarEconomics")
@Getter
@Setter
public class CarEconomics {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private double fuelConsumption;
    private int co2Emissions;
    private java.math.BigDecimal annualTax;
    private String euroStandard;

    @OneToOne(mappedBy = "carEconomics")
    private Car car;

    
}
