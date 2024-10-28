package com.kayaweers.recipes;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, String> {

    Recipe getRecipeByTitle(String title);

    List<Recipe> getRecipeByTitleContaining(String title);
}
