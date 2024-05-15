/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
@Primary
public class CategoryService implements ICategoryService{
    
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }
    
}
