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
public class Origin {
    
    private int id;
    private String name;
    private String description;
    //private Imagen img; //falta imagen
    private String continent;
    private String taste;

    public Origin() {
    }

    public Origin(int id, String name, String description, String continent, String taste) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.continent = continent;
        this.taste = taste;
    }
    
    
}
