package com.kayaweers.recipes.service;

import com.kayaweers.recipes.dto.IngredientDto;
import com.kayaweers.recipes.dto.RecipeDto;
import com.kayaweers.recipes.model.Ingredient;
import com.kayaweers.recipes.model.Recipe;
import com.kayaweers.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RecipesService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipesService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public RecipeDto getRecipe(String id){
        final Optional<Recipe> recipe = recipeRepository.findById(UUID.fromString(id));
        return recipe.map(this::mapToRecipeDto).orElse(null);
    }

    public RecipeDto getRecipeForTitle(String title){
        final Optional<Recipe> recipe = recipeRepository.getRecipeByTitle(title);
        return recipe.map(this::mapToRecipeDto).orElse(null);
    }

    public String storeRecipe(RecipeDto recipeDto) {
        final Recipe recipe = new Recipe(recipeDto.title(), recipeDto.preparation(), recipeDto.ingredients().stream().map(this::mapToIngredient).toList());
        final Recipe storedRecipe = recipeRepository.save(recipe);
        return storedRecipe.getUuid().toString();
    }

    private RecipeDto mapToRecipeDto(Recipe recipe) {
        return new RecipeDto(recipe.getUuid().toString(), recipe.getTitle(), recipe.getPreparation(), recipe.getIngredients().stream().map(this::mapToIngredientDto).toList());
    }

    private IngredientDto mapToIngredientDto(Ingredient ingredient){
        return new IngredientDto(ingredient.getUuid().toString(), ingredient.getName(), ingredient.getQuantity(), ingredient.getUnit());
    }

    private Ingredient mapToIngredient(IngredientDto ingredientDto){
        return new Ingredient(ingredientDto.name(), ingredientDto.quantity(), ingredientDto.unit());
    }

}
