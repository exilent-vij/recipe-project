package systems.exilent.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import systems.exilent.recipe.model.Recipe;
import systems.exilent.recipe.repository.RecipeRepository;

import java.util.Optional;

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
    @GetMapping("/add")
    public String addRecipeForm(Recipe recipe) {
        return "create-recipe";
    }

    @PostMapping("/add")
    public String addRecipe(Recipe recipe,Model model) {
        recipeRepository.save(recipe);
        model.addAttribute("recipes",recipeRepository.findAll());
        return "recipe-list";
    }

    @GetMapping("/find/{id}")
    public String getRecipeById(@PathVariable("id") Long id, Model model) {
        Optional<Recipe> recipe =  recipeRepository.findById(id);
        if(!recipe.isPresent()){
            return "recipe-not-found";
        }
        model.addAttribute("recipe",recipe.get());
        return "show-recipe";
    }
}
