package com.car.analytics.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CarPerformance")
@Getter
@Setter
public class CarPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int horsepower; 
    private int torque; 
    private double acceleration; 
    private int topSpeed; 
    private String transmissionType; 
    private int gears; 
    private int towingCapacity;

    @JsonIgnore
    @OneToOne(mappedBy = "carPerformance")
    private Car car;
}
