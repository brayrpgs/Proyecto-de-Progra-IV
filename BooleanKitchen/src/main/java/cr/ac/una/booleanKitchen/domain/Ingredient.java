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

/*
Los setter, getter, toString son parte de lombok, esto hace que se creen los 
antes mencionados sin tener que gastar tiempo en codificarlos.
*/

@Setter
@Getter
@ToString


public class Ingredient {
    
    //Atributos de la clase
    private int id; 
    private String code;
    private String name;
    private Float quantity;
    private Float weight;
    private int category; //Llave primaria de category
    private String categoryName;
    private Float calories;
    private String description;
    private String image;

    //Constructores
    public Ingredient() {}

    public Ingredient(int id, String code, String name, Float quantity, Float weight, int category, String categoryName, Float calories, String description, String image) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.category = category;
        this.categoryName = categoryName;
        this.calories = calories;
        this.description = description;
        this.image = image;
    }

    
    
}