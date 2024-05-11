package cr.ac.una.booleanKitchen.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Time;
import java.util.Date;
import lombok.Builder;
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
@Builder
@Entity
@Table(name = "TB_MEALPLAN")
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "description", length = Integer.MAX_VALUE, nullable = false)
    private String description;
    @Column(name = "date")
    private Date date;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "typeDiet", length = 100, nullable = false)
    private String typeDiet;
    @Column(name = "state",nullable = false)
    private Boolean state;
    @Column(name = "duration",nullable = false)
    private Time duration;
    @Column(name = "idUser",nullable = false, unique = true)
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
