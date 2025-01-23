package com.kayaweers.recipes.dto;

import com.kayaweers.recipes.model.MeasurementUnit;

public record IngredientDto(String id, String name, double quantity, MeasurementUnit unit) {
}
