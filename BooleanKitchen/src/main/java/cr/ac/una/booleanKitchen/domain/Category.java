/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author josue
 */
@Setter
@Getter
@ToString
public class Category {
    //Atributos a utilizar
    //CAT-
    private int id;
    private String idSerial;
    private String name;
    private String description;
    private Image image;
    private String label;
    private int quantity;
    private LocalDate date;

    //Constructores
    public Category() {
    }

    public Category(int id,String idSerial ,String name, String description, String label, int quantity, LocalDate date, Image image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.label = label;
        this.quantity = quantity;
        this.date = date;
        this.image = image;
        this.idSerial=idSerial;
    }
    
    
    
}
