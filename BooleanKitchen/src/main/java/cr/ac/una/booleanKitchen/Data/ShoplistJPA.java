/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.data;

import cr.ac.una.booleanKitchen.domain.ShopList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BrayRPGs
 */
@Repository
public interface ShoplistJPA extends JpaRepository<ShopList, Integer> {
    @Query("SELECT sl  FROM ShopList sl WHERE sl.name LIKE %?1% OR sl.notes LIKE %?1% OR sl.brand LIKE %?1%")
    List<ShopList> findByPartialName(String partialName);
}
