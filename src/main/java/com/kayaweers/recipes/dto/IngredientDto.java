package com.kayaweers.recipes.dto;

import com.kayaweers.recipes.model.MeasurementUnit;

public record IngredientDto(String id, String name, MeasurementUnit unit, double quantity) {
}
