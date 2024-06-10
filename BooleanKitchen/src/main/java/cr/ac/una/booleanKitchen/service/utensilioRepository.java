/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;


import cr.ac.una.booleanKitchen.domain.Utensil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ceasar
 */
public interface utensilioRepository extends JpaRepository<Utensil, Integer> {
    @Query(value = "SELECT * FROM tb_bk_utensil WHERE IDENTIFICADOR = :identificador", nativeQuery = true)
    Utensil findByIdentificador(@Param("identificador") String identificador);
}
