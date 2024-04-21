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
    private String label; //eqtiqueta de categoria: utencilio, ingrediente, receta
    private String createBy; //registro de la persona que creo la categoria
    private boolean catVisible; //poner la categoria visible o no
    private int quantity; //cantiidad de recetas asociadas
    private String comment;//agregar un comentario referente a lo que se realizo en la categoria
    private LocalDate date;// fecha de creacion de plantillas

    //Constructores
    public Category() {
    }

    public Category(int id, String idSerial, String name, String description, Image image, String label, String createBy, boolean catVisible, int quantity, String comment, LocalDate date) {
        this.id = id;
        this.idSerial = idSerial;
        this.name = name;
        this.description = description;
        this.image = image;
        this.label = label;
        this.createBy = createBy;
        this.catVisible = catVisible;
        this.quantity = quantity;
        this.comment = comment;
        this.date = date;
    }

    

   
    
    
    
}
