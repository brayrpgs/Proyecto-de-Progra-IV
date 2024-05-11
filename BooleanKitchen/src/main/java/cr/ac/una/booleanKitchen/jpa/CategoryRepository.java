/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.jpa;


import cr.ac.una.booleanKitchen.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author josue
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    @Query(value = "SELECT * FROM tb_bk_category WHERE IDENTIFICADOR = :identificador", nativeQuery = true)
    Category findByIdentificador(@Param("identificador") String identificador);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tb_bk_category WHERE IDENTIFICADOR = :identificador", nativeQuery = true)
    int deleteByIdentificador(@Param("identificador") String identificador);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_bk_category SET NOMBRE = :#{#category.name}, DESCRIPCION = :#{#category.description}, ETIQUETA = :#{#category.label}, FECHA = :#{#category.date}, VISIBLE = :#{#category.catVisible}, CREADO_POR = :#{#category.createBy}, COMENTARIO_ADMIN = :#{#category.comment}, RUTA_IMG = :#{#category.urlImagen} WHERE IDENTIFICADOR = :identificador", nativeQuery = true)
    int updateCategory(@Param("category") Category category, @Param("identificador") String identificador);
}
