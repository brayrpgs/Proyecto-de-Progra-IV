/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Entity
@Table(name="tb_bk_category")
public class Category {
    //Atributos a utilizar
    //CAT-
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",nullable = false)
    private int id;
    @Column(name="IDENTIFICADOR",nullable = false,unique = true)
    private String idSerial;
    @Column(name="NOMBRE",nullable = false,unique = true)
    private String name;
    @Column(name="DESCRIPCION")
    private String description;
    @Column(name="RUTA_IMG")
<<<<<<< HEAD
    private String image;
=======
    private String urlImagen;
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
    @Column(name="ETIQUETA")
    private String label; //eqtiqueta de categoria: utencilio, ingrediente, receta
    @Column(name="CREADO_POR")
    private String createBy; //registro de la persona que creo la categoria
    @Column(name="VISIBLE")
    private boolean catVisible; //poner la categoria visible o no
    @Transient
    private int quantity; //cantiidad de recetas asociadas
    @Column(name="COMENTARIO_ADMIN")
    private String comment;//agregar un comentario referente a lo que se realizo en la categoria
    @Column(name="FECHA")
    private LocalDate date;// fecha de creacion

    //Constructores
    public Category() {
    }

<<<<<<< HEAD
    public Category(int id, String idSerial, String name, String description, String image, String label, String createBy, boolean catVisible, int quantity, String comment, LocalDate date) {
=======
    public Category(int id, String idSerial, String name, String description, String urlImagen, String label, String createBy, boolean catVisible, int quantity, String comment, LocalDate date) {
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
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
<<<<<<< HEAD
  
=======


    

   
    
    
>>>>>>> 90eed0b00afdd806e93f131d4f117b7bde4217cb
    
}
