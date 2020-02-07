package systems.exilent.recipe.dto;

import java.util.List;

public class DisplayCategoryDTO {
    private String categoryName;
    private List<DisplayRecipeDTO> recipeDAOList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<DisplayRecipeDTO> getRecipeDAOList() {
        return recipeDAOList;
    }

    public void setRecipeDAOList(List<DisplayRecipeDTO> recipeDAOList) {
        this.recipeDAOList = recipeDAOList;
    }
}
