/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Recipe;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IRecetaService{
    
    public void Guardar(Recipe receta);
    
    public List<Recipe> getRecipes();
    
    public Recipe findByIdentificador(String identificador);
    
    public void eliminar(Recipe receta);
}