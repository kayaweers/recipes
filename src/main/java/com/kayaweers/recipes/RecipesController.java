package com.kayaweers.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String getRecipe(@RequestParam(name = "title") String title){
        Recipe recipe = recipeRepository.getRecipeByTitle(title);
        if(recipe == null){
            return "Recipe not found";
        }
        return recipe.getTitle() + " prep: " + recipe.getPreparation() + " ingredients: " + recipe.getIngredients().getFirst().getName();
    }

}
