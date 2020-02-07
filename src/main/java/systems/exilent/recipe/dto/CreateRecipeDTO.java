package systems.exilent.recipe.dto;

public class CreateRecipeDTO {

    private String description;
    private int prepTime;
    private int cookTime;
    private int servings;
    private String source;

    public long getCat_Id() {
        return cat_Id;
    }

    public void setCat_Id(long cat_Id) {
        this.cat_Id = cat_Id;
    }

    private String directions;
    private String url;
    private long cat_Id;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
