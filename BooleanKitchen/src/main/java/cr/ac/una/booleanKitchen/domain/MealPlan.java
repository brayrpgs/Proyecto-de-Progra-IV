package cr.ac.una.booleanKitchen.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
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
    @Column(name = "dateInit")
    private Date dateInit;
    @Column(name = "dateEnd")
    private Date dateEnd;
    @Column(name = "numPerson")
    private Integer numPerson;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "typeDiet", length = 100, nullable = false)
    private String typeDiet;
    @Column(name = "state",nullable = false)
    private Boolean state;
    @Column(name = "idUser",nullable = false)
    private Integer idUser;

    public MealPlan(Integer id, String name, String description, Date dateInit, Date dateEnd, Integer numPerson, Double price, String typeDiet, Boolean state, Integer idUser) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
        this.numPerson = numPerson;
        this.price = price;
        this.typeDiet = typeDiet;
        this.state = state;
        this.idUser = idUser;
    }

    

    public MealPlan() {
    }

    
}
