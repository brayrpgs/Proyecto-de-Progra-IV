package cr.ac.una.booleanKitchen.domain;

import java.sql.Time;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Brayan Rosales Perez fecha de modificacion: 14/03/2024
 */
@Setter
@Getter
@ToString
public class MealPlan {

    private Integer id;
    private String name;
    private String description;
    private Date date;
    private Double price;
    private String typeDiet;
    private Boolean state;
    private Time duration;
    private Integer idUser;

    public MealPlan(Integer  id, String name, String description, Date date, Double price, String typeDiet, Boolean state, Time duration, Integer idUser) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.price = price;
        this.typeDiet = typeDiet;
        this.state = state;
        this.duration = duration;
        this.idUser = idUser;
    }
}
