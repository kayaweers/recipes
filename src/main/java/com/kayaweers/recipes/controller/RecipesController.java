package com.kayaweers.recipes.controller;

import com.kayaweers.recipes.dto.RecipeDto;
import com.kayaweers.recipes.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/recipes")
public class RecipesController {

    private final RecipesService recipesService;

    @Autowired
    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/info")
    public String getInfo(){
        return "Recipes application";
    }

    @GetMapping("/{id}")
    public RecipeDto getRecipe(@PathVariable String id){
        final RecipeDto recipe = recipesService.getRecipe(id);
        if (recipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found");
        }
        return recipe;
    }

    @GetMapping("/search")
    public RecipeDto searchRecipe(@RequestParam(name = "title") String title){
        final RecipeDto recipe = recipesService.getRecipeForTitle(title);
        if (recipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found");
        }
        return recipe;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String addRecipe(@RequestBody RecipeDto recipeDto){
        return recipesService.storeRecipe(recipeDto);
    }

}
