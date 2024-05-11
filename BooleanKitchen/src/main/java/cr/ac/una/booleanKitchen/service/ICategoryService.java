/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;


import cr.ac.una.booleanKitchen.domain.Category;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author josue
 */
public interface ICategoryService {
     public int guardar(Category Cat);
    
    public List getCategorias();
    
    public void EliminarCategoria (int codigo);
    
    Category getCategory(String cat);
    
    public boolean DeleteCategory(String cat);
    
    public boolean modifyCat(Category cat);
    
    public Page<Category> getPage(Pageable page);
    
}
