package com.kayaweers.recipes.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(schema="recipes")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String name;
    private double quantity;
    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;

    protected Ingredient() {}

    public Ingredient(String name, double quantity, MeasurementUnit unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public UUID getUuid() {
        return uuid;
    }

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
