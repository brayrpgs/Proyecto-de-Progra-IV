/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Preparation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Daniel Briones
 */
public interface preparationRepository extends JpaRepository<Preparation, Integer>{
    @Query(value = "SELECT * FROM tb_bk_preparation WHERE IDENTIFICADOR = :identificador", nativeQuery = true)
    Preparation findByIdentificadorPrep(@Param("identificador") String identificador);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tb_bk_preparation WHERE IDENTIFICADOR = :identificador", nativeQuery = true)
    int deleteByIdentificador(@Param("identificador") String identificador);
}
