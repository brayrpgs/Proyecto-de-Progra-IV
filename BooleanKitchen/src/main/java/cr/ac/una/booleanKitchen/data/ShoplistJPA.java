/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.data;

import cr.ac.una.booleanKitchen.domain.ShopList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author BrayRPGs
 */
public interface ShoplistJPA extends JpaRepository<ShopList, Integer> {
    
}
