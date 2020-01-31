package systems.exilent.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import systems.exilent.recipe.repository.RecipeRepository;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;
    @GetMapping("/show")
    public String getRecipes(Model model){
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipe-list";
    }
}
