package com.kayaweers.recipes.service;

import com.kayaweers.recipes.dto.IngredientDto;
import com.kayaweers.recipes.dto.RecipeDto;
import com.kayaweers.recipes.model.Ingredient;
import com.kayaweers.recipes.model.MeasurementUnit;
import com.kayaweers.recipes.model.Recipe;
import com.kayaweers.recipes.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipesServiceTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipesService recipesService;

    private static final String RECIPE_ID = "34bbbc43-13d1-4981-a608-71c338acd703";

    @Test
    void testGetRecipeExisting() {
        Recipe expectedRecipe = getRecipe();
        when(recipeRepository.findById(any())).thenReturn(Optional.of(expectedRecipe));

        RecipeDto recipe = recipesService.getRecipe(RECIPE_ID);

        assertEquals(expectedRecipe.getTitle(), recipe.title());
        assertEquals(expectedRecipe.getPreparation(), recipe.preparation());

        Ingredient expectedIngredient = expectedRecipe.getIngredients().getFirst();
        IngredientDto ingredient = recipe.ingredients().getFirst();
        assertEquals(expectedIngredient.getName(), ingredient.name());
        assertEquals(expectedIngredient.getQuantity(), ingredient.quantity());
        assertEquals(expectedIngredient.getUnit(), ingredient.unit());
    }

    @Test
    void testGetRecipeNotExisting() {
        when(recipeRepository.findById(any())).thenReturn(Optional.empty());
        RecipeDto recipe = recipesService.getRecipe(UUID.randomUUID().toString());
        assertNull(recipe);
    }

    @Test
    void testGetRecipeForTitleExisting(){
        Recipe expectedRecipe = getRecipe();
        when(recipeRepository.getRecipeByTitle(any())).thenReturn(Optional.of(expectedRecipe));

        RecipeDto recipe = recipesService.getRecipeForTitle("Pasta pesto");

        assertEquals(expectedRecipe.getTitle(), recipe.title());

        Ingredient expectedIngredient = expectedRecipe.getIngredients().getFirst();
        IngredientDto ingredient = recipe.ingredients().getFirst();
        assertEquals(expectedIngredient.getName(), ingredient.name());
    }

    @Test
    void testGetRecipeForTitleNotExisting() {
        when(recipeRepository.getRecipeByTitle(any())).thenReturn(Optional.empty());
        RecipeDto recipe = recipesService.getRecipeForTitle(UUID.randomUUID().toString());
        assertNull(recipe);
    }

    @Test
    void testStoreRecipe(){
        IngredientDto ingredient = new IngredientDto(null, "pesto", 0.1, MeasurementUnit.L);
        RecipeDto recipe = new RecipeDto(null, "Pasta pesto", "prep", Collections.singletonList(ingredient));
        when(recipeRepository.save(any())).thenReturn(getRecipe());

        String uuid = recipesService.storeRecipe(recipe);

        assertNotNull(uuid);
        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(recipeRepository).save(recipeCaptor.capture());
        assertEquals(recipe.title(), recipeCaptor.getValue().getTitle());
        assertEquals(recipe.preparation(), recipeCaptor.getValue().getPreparation());
        Ingredient actualIngredient = recipeCaptor.getValue().getIngredients().getFirst();
        assertEquals(ingredient.name(), actualIngredient.getName());
        assertEquals(ingredient.quantity(), actualIngredient.getQuantity());
        assertEquals(ingredient.unit(), actualIngredient.getUnit());
    }

    private Recipe getRecipe() {
        Ingredient pasta = new Ingredient(UUID.fromString("6c342641-f39a-49b5-b4de-222871df504b"),"pasta", 150, MeasurementUnit.G);
        return new Recipe(UUID.fromString(RECIPE_ID),"Pasta pesto", "prep", Collections.singletonList(pasta));
    }

}