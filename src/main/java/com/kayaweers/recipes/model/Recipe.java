package com.kayaweers.recipes.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(schema="recipes")
public class Recipe {

    @Id
    private UUID uuid;
    private String title;
    private String preparation;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List<Ingredient> ingredients;

    protected Recipe() {}

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getPreparation() {
        return preparation;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
