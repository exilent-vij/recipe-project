package systems.exilent.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import systems.exilent.recipe.model.Recipe;
import systems.exilent.recipe.repository.RecipeRepository;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/show")
    public String getRecipes(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipe-list";
    }

    @GetMapping("/add")
    public String addRecipeForm(Recipe recipe) {
        return "create-recipe";
    }

    @PostMapping("/add")
    public String addRecipe(Recipe recipe, Model model) {
        recipeRepository.save(recipe);
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipe-list";
    }
}
