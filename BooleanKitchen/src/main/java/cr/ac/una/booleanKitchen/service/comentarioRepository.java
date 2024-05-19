
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Usuario
 */
public interface comentarioRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c WHERE c.idRecipeSelected.identificador = :identificador")
    List<Comment> findByRecipeIdentificador(@Param("identificador") String identificador);
    
    
    Comment findByIdentificador(String identificador);
}
