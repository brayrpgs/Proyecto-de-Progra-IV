
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 *
 * @author Usuario
 */
public interface comentarioRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c WHERE c.idRecipeSelected.identificador = :identificador")
    Page<Comment> findByRecipeId(String identificador, Pageable pageable);
    
    
    Comment findByIdentificador(String identificador);
}
