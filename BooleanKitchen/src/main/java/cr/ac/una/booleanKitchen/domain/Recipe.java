
package cr.ac.una.booleanKitchen.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.LinkedList;
/**
 *
 * @author Daniel Briones
 */
@Setter
@Getter
@ToString
public class Recipe {
    private int id;
    private LinkedList<Ingredient> ingredientList;
    private String name;
    private String origin;
    private LinkedList<Utensil> utensilList;
    private LinkedList<Comment> commentList;
    private Category category;
    private Preparation preparation;
    private int calificacion;
    private Image image;
    
    public Recipe() {
    }

    public Recipe(int id, LinkedList<Ingredient> ingredientList, String name, String origin,
            LinkedList<Utensil> utensilList, LinkedList<Comment> commentList, Category category,
            Preparation preparation, int calificacion, Image image) {
        this.id = id;
        this.ingredientList = ingredientList;
        this.name = name;
        this.origin = origin;
        this.utensilList = utensilList;
        this.commentList = commentList;
        this.category = category;
        this.preparation = preparation;
        this.calificacion = calificacion;
        this.image = image;
    }

    
}
