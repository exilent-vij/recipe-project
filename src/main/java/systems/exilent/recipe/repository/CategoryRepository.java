package systems.exilent.recipe.repository;

import org.springframework.data.repository.CrudRepository;
import systems.exilent.recipe.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> { }