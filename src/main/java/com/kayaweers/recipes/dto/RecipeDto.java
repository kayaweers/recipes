package com.kayaweers.recipes.dto;

import java.util.List;

public record RecipeDto(String id, String title, String preparation, List<IngredientDto> ingredients) { }
