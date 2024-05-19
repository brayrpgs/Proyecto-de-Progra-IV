package cr.ac.una.booleanKitchen.domain;

import org.hibernate.annotations.Formula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

@Entity
@Table(name = "tb_bk_ingredient", schema = "public")
public class Ingredient {
    
    //Atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id; 

    @Column(name = "IDENTIFICADOR")
    private String code;

    @Column(name = "NOMBRE")
    private String name;

    @Column(name = "CANTIDAD")
    private Float quantity;

    @Column(name = "PESO")
    private Float weight;

    @Column(name = "CALORIAS")
    private Float calories;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "RUTA_IMG")
    private String image;

    @Column(name = "ID_CATEGORIA")
    private int category;

    @Formula("(SELECT c.NOMBRE FROM tb_bk_category c WHERE c.ID = ID_CATEGORIA)")
    private String categoryName;
    

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