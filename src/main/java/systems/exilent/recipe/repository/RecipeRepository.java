package systems.exilent.recipe.repository;

import org.springframework.data.repository.CrudRepository;
import systems.exilent.recipe.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
