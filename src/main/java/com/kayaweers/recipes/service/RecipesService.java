package com.kayaweers.recipes.service;

import com.kayaweers.recipes.dto.IngredientDto;
import com.kayaweers.recipes.dto.RecipeDto;
import com.kayaweers.recipes.model.Ingredient;
import com.kayaweers.recipes.model.Recipe;
import com.kayaweers.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipesService {

    @Autowired
    private RecipeRepository recipeRepository;

    public RecipeDto getRecipeForTitle(String title){
        Recipe recipe = recipeRepository.getRecipeByTitle(title);
        if (recipe == null) {
            return null;
        }
        return new RecipeDto(recipe.getTitle(), recipe.getPreparation(), recipe.getIngredients().stream().map(this::transferIngredient).toList());
    }

    private IngredientDto transferIngredient(Ingredient ingredient){
        return new IngredientDto(ingredient.getName(), ingredient.getUnit(), ingredient.getQuantity());
    }

}
