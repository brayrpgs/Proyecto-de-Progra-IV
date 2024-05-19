
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Usuario
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{
     //Seleccionar categoria media identificador y no PK
    @Query(value = "SELECT * FROM tb_bk_category WHERE IDENTIFICADOR = :identificador", nativeQuery = true)
    Category findByIdentificador(@Param("identificador") String identificador);

    //Eliminar mediante Identificador
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tb_bk_category WHERE IDENTIFICADOR = :identificador", nativeQuery = true)
    int deleteByIdentificador(@Param("identificador") String identificador);

    //Modificar mediante identificador
    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_bk_category SET NOMBRE = :#{#category.name}, DESCRIPCION = :#{#category.description}, ETIQUETA = :#{#category.label}, FECHA = :#{#category.date}, VISIBLE = :#{#category.catVisible}, CREADO_POR = :#{#category.createBy}, COMENTARIO_ADMIN = :#{#category.comment}, RUTA_IMG = :#{#category.image} WHERE IDENTIFICADOR = :identificador", nativeQuery = true)
    int updateCategory(@Param("category") Category category, @Param("identificador") String identificador);
}
