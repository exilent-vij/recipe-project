package systems.exilent.recipe.controller;

import org.aspectj.weaver.Iterators;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import systems.exilent.recipe.dto.*;
import systems.exilent.recipe.model.Category;
import systems.exilent.recipe.model.Recipe;
import systems.exilent.recipe.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/show")
    public ResponseEntity<List<DisplayCategoryRecipeCount>> getCategories() {
        Iterable<Category> categories = categoryRepository.findAll();
        List<DisplayCategoryRecipeCount> categoryDAOS = new ArrayList<>();
        for (Category category : categories) {
            DisplayCategoryRecipeCount categoryDAO = new DisplayCategoryRecipeCount();
            categoryDAO.setCategoryName(category.getCatName());
            categoryDAO.setRecipeCount(category.getRecipes().size());
            categoryDAOS.add(categoryDAO);
        }
        return new ResponseEntity<>(categoryDAOS, HttpStatus.OK);
    }
//**********************************************************************************************************************
    @GetMapping("/details")
    public ResponseEntity<List<DisplayCategoryRecipeDetails>> getCategory() {
        Iterable<Category> categories = categoryRepository.findAll();
        List<DisplayCategoryRecipeDetails> categoryRecipeDetails = new ArrayList<>();
        for (Category category : categories) {
            DisplayCategoryRecipeDetails categoryRecipe = new DisplayCategoryRecipeDetails();
            categoryRecipe.setCategoryName(category.getCatName());
            List<DisplayRecipeDTO> displayRecipeDTOList = new ArrayList<>();
            for (Recipe recipe : category.getRecipes()) {
                System.out.println(recipe.getDescription());
                DisplayRecipeDTO displayRecipeDTO = new DisplayRecipeDTO();
                displayRecipeDTO.setCategoryName(recipe.getCategory().getCatName());
                displayRecipeDTO.setCookTime(recipe.getCookTime());
                displayRecipeDTO.setDescription((recipe.getDescription()));
                displayRecipeDTO.setPrepTime(recipe.getPrepTime());
                displayRecipeDTO.setServings(recipe.getServings());
                displayRecipeDTO.setSource(recipe.getSource());
                displayRecipeDTOList.add(displayRecipeDTO);
            }
            categoryRecipe.setDisplayRecipeDTOS(displayRecipeDTOList);
            categoryRecipeDetails.add(categoryRecipe);
        }
        return new ResponseEntity<>(categoryRecipeDetails, HttpStatus.OK);

    }
//**********************************************************************************************************************
    @PutMapping("/update")
    public ResponseEntity<Object> updateCategory(@RequestBody UpdateCategoryDTO updateCategoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(updateCategoryDTO.getId());
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO();
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setCatName(updateCategoryDTO.getCategoryName());
            categoryRepository.save(category);
            return new ResponseEntity<>(updateCategoryDTO, HttpStatus.OK);
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
//**********************************************************************************************************************

    @PostMapping("/add")
    public ResponseEntity<Object> addCategory(@RequestBody CreateCategoryDTO createCategoryDTO) {
        Category category = new Category();
        category.setCatName(createCategoryDTO.getCategoryName());
        Category newCategory = categoryRepository.save(category);
        DisplayCategoryDTO displayCategoryDTO = new DisplayCategoryDTO();
        displayCategoryDTO.setCategoryName(newCategory.getCatName());
        displayCategoryDTO.setRecipeDAOList(new ArrayList<>());
        return new ResponseEntity<>(displayCategoryDTO, HttpStatus.OK);
    }
//**********************************************************************************************************************

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());


        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
//**********************************************************************************************************************
    @GetMapping("show/{id}")
    public ResponseEntity<DisplayCategoryDTO> getCategoryById(@PathVariable("id") Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        DisplayCategoryDTO displayCategoryDTO = new DisplayCategoryDTO();
        Iterable<Recipe> recipes = category.get().getRecipes();
        List<DisplayRecipeDTO> recipeDAOS = new ArrayList<>();
        for (Recipe recipe : recipes) {
            DisplayRecipeDTO displayRecipeDTO = new DisplayRecipeDTO();
            displayRecipeDTO.setCookTime(recipe.getCookTime());
            displayRecipeDTO.setDescription(recipe.getDescription());
            displayRecipeDTO.setPrepTime(recipe.getPrepTime());
            displayRecipeDTO.setServings(recipe.getServings());
            displayRecipeDTO.setCategoryName(category.get().getCatName());
            recipeDAOS.add(displayRecipeDTO);

        }
        displayCategoryDTO.setRecipeDAOList(recipeDAOS);
        displayCategoryDTO.setCategoryName(category.get().getCatName());
        return new ResponseEntity<>(displayCategoryDTO, HttpStatus.OK);


    }
//**********************************************************************************************************************
}