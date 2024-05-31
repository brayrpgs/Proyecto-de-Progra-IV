package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.domain.Ingredient;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Integer> {

    Ingredient save(Ingredient ingredient);
    Optional<Ingredient> findByCode(String code);
    Optional<Ingredient> findByName(String name);
    void deleteByCode(String code);
    
    @Query("SELECT c FROM Category c WHERE c.label = 'INGREDIENTE' ORDER BY c.name")
    LinkedList<Category> getCategories();
}