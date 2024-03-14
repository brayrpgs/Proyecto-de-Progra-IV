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
 * @author kenda
 */
@Setter
@Getter
@ToString
public class Ingredient {
    
    //Atributos de la clase
    private String id; 
    private String name;
    private Float quantity;
    private Float weight;
    private Category category; 
    private Float calories;
    private String description;
    private String image;

    //Constructores
    public Ingredient() {}

    public Ingredient(String id, String name, Float quantity, Float weight, Category category, Float calories, String description, String image) {
        
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.category = category;
        this.calories = calories;
        this.description = description;
        this.image = image;
        
    }
    
}
