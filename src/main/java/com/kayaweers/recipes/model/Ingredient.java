package com.kayaweers.recipes.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(schema="recipes")
public class Ingredient {

    @Id
    private UUID uuid;
    private double quantity;
    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;
    private String name;

    protected Ingredient() {}

    public double getQuantity() {
        return quantity;
    }

    public MeasurementUnit getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }
}
