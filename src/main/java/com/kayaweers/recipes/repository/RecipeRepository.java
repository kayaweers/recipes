package com.kayaweers.recipes.repository;

import com.kayaweers.recipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeRepository extends CrudRepository<Recipe, UUID> {

    Optional<Recipe> getRecipeByTitle(String title);

    List<Recipe> getRecipeByTitleContaining(String title);
}
