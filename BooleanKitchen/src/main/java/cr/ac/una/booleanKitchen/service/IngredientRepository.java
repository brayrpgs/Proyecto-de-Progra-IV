package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Ingredient;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Integer> {

}