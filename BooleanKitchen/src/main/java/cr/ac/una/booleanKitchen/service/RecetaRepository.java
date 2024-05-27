package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Recipe;
import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Usuario
 */
public interface RecetaRepository extends JpaRepository<Recipe, Integer> {

    @Query("SELECT r FROM Recipe r JOIN FETCH r.preparacion p JOIN FETCH p.preparationList")
    List<Recipe> findAllWithPreparacionAndPasos();

    @Query("SELECT r FROM Recipe r JOIN FETCH r.preparacion p JOIN FETCH p.preparationList WHERE r.identificador = :identificador")
    Recipe findOneRecipe(@Param("identificador") String identificador);

    
    @Transactional
    @Modifying
    @Query("DELETE FROM Step s WHERE s.preparation.id IN (SELECT p.id FROM Preparation p WHERE p.idRecipe.identificador = :identificador)")
    void deleteStepsByRecetaIdentificador(@Param("identificador") String identificador);

    @Transactional
    @Modifying
    @Query("DELETE FROM Preparation p WHERE p.idRecipe.identificador = :identificador")
    void deletePreparacionByRecetaIdentificador(@Param("identificador") String identificador);

    @Transactional
    @Modifying
    @Query("DELETE FROM Recipe r WHERE r.identificador = :identificador")
    void deleteByIdentificador(@Param("identificador") String identificador);

    Recipe findByIdentificador(String identificador);

    @Query(value = "SELECT * FROM tb_bk_recipe ORDER BY CALIFICACION DESC LIMIT 10", nativeQuery = true)
    List<Recipe> findTop10ByOrderByCalificacionDesc();

    Page<Recipe> findByNameContainingIgnoreCase(String name, Pageable pageable);

}




