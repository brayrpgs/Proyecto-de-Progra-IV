/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.MealPlan;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author BrayRPGs
 */
public interface IServiceMealplan {
    public Boolean save(MealPlan m);
    public Page<MealPlan> getAll(Integer numPage);
    public Boolean delete(Integer id);
    public List<MealPlan> search(String data);
}
