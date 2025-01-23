package com.kayaweers.recipes.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(schema="recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String title;
    private String preparation;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id", nullable = false)
    private List<Ingredient> ingredients;

    protected Recipe() {}

    public Recipe(String title, String preparation, List<Ingredient> ingredients) {
        this.title = title;
        this.preparation = preparation;
        this.ingredients = ingredients;
    }

    public Recipe(UUID uuid, String title, String preparation, List<Ingredient> ingredients) {
        this.uuid = uuid;
        this.title = title;
        this.preparation = preparation;
        this.ingredients = ingredients;
    }

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
