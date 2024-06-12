/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.Data.MealplanJPA;
import cr.ac.una.booleanKitchen.domain.MealPlan;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author BrayRPGs
 */
@Service
@Primary
public class ServiceMealPlan implements IServiceMealplan{
    
    @Autowired
    private MealplanJPA jpa;

    @Override
    public Boolean save(MealPlan m) {
        jpa.save(m);
        return true;
    }

    @Override
    public Page<MealPlan> getAll(Integer numPage) {
        Pageable pageable = PageRequest.of(numPage, 4);
        return jpa.findAll(pageable);
    }

    @Override
    public Boolean delete(Integer id) {
        jpa.deleteById(id);
        return true;
    }

    @Override
    public List<MealPlan> search(String data) {
         return jpa.findByPartialName(data);
    }

    public ServiceMealPlan(MealplanJPA jpa) {
        this.jpa = jpa;
    }

    public ServiceMealPlan() {
    }
    
    
}
