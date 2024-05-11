package cr.ac.una.booleanKitchen.domain;

import java.util.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Brayan Rosales Perez , Fecha de modificacion: 14/03/2024
 */
@Getter
@Setter
@ToString
@Builder
@Entity
public class ShopList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Float amount;
    private String notes;
    private String brand;
    private Boolean state;
    private Date date;
    private Integer idUser;

    public ShopList(Integer id, String name, Float amount, String notes, String brand, Boolean state, Date date,
            Integer idUser) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.notes = notes;
        this.brand = brand;
        this.state = state;
        this.date = date;
        this.idUser = idUser;
    }

    public ShopList() {
    }

}
