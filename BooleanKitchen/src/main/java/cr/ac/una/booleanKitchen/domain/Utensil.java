/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Ceasar Calvo ///Fecha de modificacion 14/03/2024
 */

@Setter
@Getter
@ToString
public class Utensil {
    
    private int id;
    private String name;
    private String material;
    private Category category;
    private float price;
    private int quantity;
    //private Imagen img; //falta imagen
    private String description;

    public Utensil() {
    }

    public Utensil(int id, String name, String material, Category category, float price, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }
    
    
    
}
