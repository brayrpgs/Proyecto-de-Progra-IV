package cr.ac.una.booleanKitchen.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Category {

    //Categoria provicional mientras josue sube el modulo
    Integer id;
    String nameCategory;


    public Category() {
    }
   

    public Category(Integer id, String nameCategory) {
        this.id = id;
        this.nameCategory = nameCategory;
    }

    
}
