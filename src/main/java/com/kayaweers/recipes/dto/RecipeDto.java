package com.kayaweers.recipes.dto;

import java.util.List;

public record RecipeDto(String title, String preparation, List<IngredientDto> ingredients) { }