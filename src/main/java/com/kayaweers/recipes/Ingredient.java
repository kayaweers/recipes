package com.kayaweers.recipes;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(schema="recipes")
public class Ingredient {

    @Id
    private UUID uuid;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;
    private String name;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    protected Ingredient() {}

    public int getQuantity() {
        return quantity;
    }

    public MeasurementUnit getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
