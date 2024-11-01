package com.kayaweers.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/recipes")
public class RecipesController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/info")
    public String getInfo(){
        return "Recipes application";
    }

    @GetMapping("/search")
    public Recipe getRecipe(@RequestParam(name = "title") String title){
        Recipe recipe = recipeRepository.getRecipeByTitle(title);
        if (recipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found");
        }
        return recipe;
    }

}
