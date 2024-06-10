/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;
import cr.ac.una.booleanKitchen.domain.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



/**
 *
 * @author Ceasar
 */

public interface OriginRepoisitory extends JpaRepository<Origin,Integer>{
        @Query(value = "SELECT * FROM tb_bk_Origin WHERE IDENTIFICADOR = :identificador", nativeQuery = true)
    Origin findByIdentificador(@Param("identificador") String identificador);
}
