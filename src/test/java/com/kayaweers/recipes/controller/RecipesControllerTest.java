package com.kayaweers.recipes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kayaweers.recipes.dto.IngredientDto;
import com.kayaweers.recipes.dto.RecipeDto;
import com.kayaweers.recipes.model.MeasurementUnit;
import com.kayaweers.recipes.service.RecipesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipesController.class)
class RecipesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipesService recipesService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testGetInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/info"))
                .andExpect(status().isOk())
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("Recipes application")));
    }

    @Test
    void testGetRecipesExists() throws Exception {
        final String recipeId = "123";
        when(recipesService.getRecipe(eq(recipeId))).thenReturn(getRecipeDto());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/recipes/{id}", recipeId))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResult = result.getResponse().getContentAsString();
        RecipeDto resultDto = objectMapper.readValue(jsonResult, RecipeDto.class);
        assertEquals(getRecipeDto(), resultDto);
    }

    @Test
    void testGetRecipesNotFound() throws Exception {
        final String recipeId = "456";
        when(recipesService.getRecipe(eq(recipeId))).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/{id}", recipeId))
                .andExpect(result -> assertTrue(result.getResolvedException().getMessage().contains("Recipe not found")));
    }

    @Test
    void testSearchRecipe() throws Exception {
        final String recipeTitle = "Pasta pesto";
        when(recipesService.getRecipeForTitle(eq(recipeTitle))).thenReturn(getRecipeDto());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/recipes/search").param("title", recipeTitle))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResult = result.getResponse().getContentAsString();
        RecipeDto resultDto = objectMapper.readValue(jsonResult, RecipeDto.class);
        assertEquals(getRecipeDto(), resultDto);
    }

    @Test
    void testSearchRecipeNotFound() throws Exception {
        final String recipeTitle = "Not existing";
        when(recipesService.getRecipeForTitle(eq(recipeTitle))).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/search").param("title", recipeTitle))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddRecipe() throws Exception {
        when(recipesService.storeRecipe(getRecipeDto())).thenReturn("123");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getRecipeDto())))
                .andExpect(status().isCreated())
                .andReturn();
        assertEquals("123", result.getResponse().getContentAsString());
    }

    private RecipeDto getRecipeDto() {
        IngredientDto pasta = new IngredientDto("444", "pasta", 150, MeasurementUnit.G);
        return new RecipeDto("123", "Pasta pesto", "prep", Collections.singletonList(pasta));
    }
}