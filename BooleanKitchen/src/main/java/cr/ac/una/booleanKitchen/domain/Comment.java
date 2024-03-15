package cr.ac.una.booleanKitchen.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 *
 * @author Daniel Briones
 */
@Setter
@Getter
@ToString
public class Comment {
    private int id;
    private int idRecipeSelected;
    private int idUser;
    private LocalDate date;
    private int punctuation;
    private String commentText;
    private int reaction;
    private String title;
    
    public Comment() {
    }

    public Comment(int id, int idRecipeSelected, int idUser, LocalDate date, int punctuation, String commentText,
            int reaction, String title) {
        this.id = id;
        this.idRecipeSelected = idRecipeSelected;
        this.idUser = idUser;
        this.date = date;
        this.punctuation = punctuation;
        this.commentText = commentText;
        this.reaction = reaction;
        this.title = title;
    }

    

}
