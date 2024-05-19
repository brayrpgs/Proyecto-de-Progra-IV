/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

<<<<<<< HEAD
=======

>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
import cr.ac.una.booleanKitchen.domain.Category;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
<<<<<<< HEAD
 * @author Usuario
 */
public interface ICategoryService {
    public List<Category> getCategory();

     public int guardar(Category Cat);
      public void EliminarCategoria (int codigo);
    
    Category getCategoryOnly(String cat);
=======
 * @author josue
 */
public interface ICategoryService {
     public int guardar(Category Cat);
    
    public List getCategorias();
    
    public void EliminarCategoria (int codigo);
    
    Category getCategory(String cat);
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
    
    public int DeleteCategory(String cat);
    
    public boolean modifyCat(Category cat);
    
    public Page<Category> getPage(Pageable page);
<<<<<<< HEAD
=======
    
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
}
