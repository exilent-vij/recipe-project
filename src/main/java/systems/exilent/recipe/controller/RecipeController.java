package systems.exilent.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import systems.exilent.recipe.dto.DisplayRecipeDTO;
import systems.exilent.recipe.dto.CreateRecipeDTO;
import systems.exilent.recipe.model.Category;
import systems.exilent.recipe.model.Recipe;
import systems.exilent.recipe.repository.CategoryRepository;
import systems.exilent.recipe.repository.RecipeRepository;

import java.util.Optional;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/show")
    public ResponseEntity<Iterable<Recipe>> getRecipe() {

        return new ResponseEntity<>(recipeRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addRecipe(@RequestBody CreateRecipeDTO recipeDAO) {
        Recipe recipe = new Recipe();
        recipe.setDescription(recipeDAO.getDescription());
        recipe.setCookTime(recipeDAO.getCookTime());
        recipe.setDirections(recipeDAO.getDirections());
        recipe.setPrepTime(recipeDAO.getPrepTime());
        recipe.setServings(recipeDAO.getServings());
        recipe.setSource(recipeDAO.getSource());
        recipe.setUrl(recipeDAO.getUrl());
        Optional<Category> category = categoryRepository.findById(recipeDAO.getCat_Id());
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipe.setCategory(category.get());
        Recipe newRecipe = recipeRepository.save(recipe);

        DisplayRecipeDTO displayRecipeDTO = new DisplayRecipeDTO();

        displayRecipeDTO.setSource(recipe.getSource());
        displayRecipeDTO.setServings(recipe.getServings());
        displayRecipeDTO.setPrepTime(recipe.getPrepTime());
        displayRecipeDTO.setDescription(recipe.getDescription());
        displayRecipeDTO.setCookTime(recipe.getCookTime());
        displayRecipeDTO.setCategoryName(recipe.getCategory().getCatName());
        displayRecipeDTO.setUrl(recipe.getUrl());
        displayRecipeDTO.setDirection(recipe.getDirections());

        return new ResponseEntity<>(displayRecipeDTO, HttpStatus.OK);


    }

}
