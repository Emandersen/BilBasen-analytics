package com.car.analytics.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PriceHistory")
@Getter
@Setter
public class CarPriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;
    private String recordedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_external_id", referencedColumnName = "car_external_id")
    private Car car;
}