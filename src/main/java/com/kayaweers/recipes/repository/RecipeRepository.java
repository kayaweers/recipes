package com.kayaweers.recipes.repository;

import com.kayaweers.recipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, String> {

    Recipe getRecipeByTitle(String title);

    List<Recipe> getRecipeByTitleContaining(String title);
}
