package systems.exilent.recipe.dto;

import systems.exilent.recipe.model.Recipe;

import java.util.List;

public class DisplayCategoryRecipeDetails {
    private String CategoryName;
    private List<DisplayRecipeDTO> displayRecipeDTOS;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public List<DisplayRecipeDTO> getDisplayRecipeDTOS() {
        return displayRecipeDTOS;
    }

    public void setDisplayRecipeDTOS(List<DisplayRecipeDTO> displayRecipeDTOS) {
        this.displayRecipeDTOS = displayRecipeDTOS;
    }

    public void getDisplayRecipeDTOS(List<Recipe> recipes) {
    }
}
