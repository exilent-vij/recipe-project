package systems.exilent.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import systems.exilent.recipe.model.Category;
import systems.exilent.recipe.model.Recipe;
import systems.exilent.recipe.repository.CategoryRepository;

import java.util.Locale;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/show")
    public String getCategory(Model model) {
        model.addAttribute("category", categoryRepository.findAll());
        return ("category-name");
    }

    @GetMapping("/add")
    public String addCategoryForm(Category category) {
        return "create-category";
    }

    @PostMapping("/add")
    public String addCategory(Category category, Model model) {
        categoryRepository.save(category);
        model.addAttribute("category", categoryRepository.findAll());
        return "category-name";
    }


}
