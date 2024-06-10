/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;


import cr.ac.una.booleanKitchen.domain.Utensil;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Ceasar
 */
public interface IUtensilioService {
    public void crearUtensilio(Utensil utensilio);

    public List<Utensil> leerUtensilio();
    
    public Utensil getUtensilio(String identificador);

    public void EliminarUtensilio(int codigo);

    public Page<Utensil>getPageUtensil(Pageable pageable);
}
