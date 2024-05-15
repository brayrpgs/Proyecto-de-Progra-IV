
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Usuario
 */
public interface RecetaRepository extends JpaRepository<Recipe, Integer>{
//    @Query("SELECT r FROM Receta r JOIN FETCH r.preparacion p JOIN FETCH p.preparationList")
//    List<Recipe> findAllWithPreparacionAndPasos();
//    
//    @Query("SELECT r FROM Receta r JOIN FETCH r.preparacion p JOIN FETCH p.preparationList WHERE r.identificador = :identificador")
//    Recipe findOneRecipe(@Param("identificador") String identificador);
    Recipe findByIdentificador(String identificador);
}
