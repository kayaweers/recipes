package com.kayaweers.recipes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipesController {

    @GetMapping("/info")
    public String getInfo(){
        return "Recipes application";
    }

}
