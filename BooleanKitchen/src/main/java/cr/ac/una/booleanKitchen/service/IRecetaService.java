/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Recipe;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Usuario
 */
public interface IRecetaService {

    public void Guardar(Recipe receta);


    public Recipe findByIdentificador(String identificador);

    public void eliminar(Recipe receta);

    public boolean DeleteRecipe(String idSerial);

    public List<Recipe> findTop10ByOrderByCalificacionDesc();

    public Page<Recipe> findByNameContainingIgnoreCase(String name, Pageable pageable);

    public Page<Recipe> paginacionRecetas(Pageable pageable);
}