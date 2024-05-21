/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Utensil;
import java.util.List;

/**
 *
 * @author josue
 */
public interface IUtensilioService {
    public void crearUtensilio(Utensil utensilio);

    public List<Utensil> leerUtensilio();
    
    public Utensil getUtensilio(String identificador);

    public void EliminarUtensilio(int codigo);
}
