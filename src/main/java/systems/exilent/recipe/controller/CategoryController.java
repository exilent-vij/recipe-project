package systems.exilent.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import systems.exilent.recipe.dao.CategoryDAO;
import systems.exilent.recipe.model.Category;
import systems.exilent.recipe.repository.CategoryRepository;

import java.util.Optional;

@RestController

@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;



    @GetMapping("/show")
    public ResponseEntity<Iterable<Category>> getCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCategory(@RequestBody CategoryDAO categoryDAO) {
        Category category = new Category();
        category.setCatName(categoryDAO.getCategoryName());
        Category newCategory = categoryRepository.save(category);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    @GetMapping("show/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category.get(), HttpStatus.OK);


    }

}

