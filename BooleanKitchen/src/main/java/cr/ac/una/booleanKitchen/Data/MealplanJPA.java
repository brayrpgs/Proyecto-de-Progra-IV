/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.Data;

import cr.ac.una.booleanKitchen.domain.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BrayRPGs
 */
@Repository
public interface MealplanJPA extends JpaRepository<MealPlan, Integer> {
  @Query("SELECT sl  FROM MealPlan sl WHERE sl.name LIKE %?1% OR sl.description LIKE %?1% OR sl.typeDiet LIKE %?1%")
    List<MealPlan> findByPartialName(String partialName);

}
