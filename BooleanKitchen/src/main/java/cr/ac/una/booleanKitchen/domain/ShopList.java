package cr.ac.una.booleanKitchen.domain;

import jakarta.persistence.Column;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "tb_Shoplist")
public class ShopList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "amount", nullable = false)
    private Float amount;
    @Column(name = "notes", length = Integer.MAX_VALUE, nullable = false)
    private String notes;
    @Column(name = "brand", length = 50, nullable = false)
    private String brand;
    @Column(name = "state", nullable = false)
    private Boolean state;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "idUser", nullable = false, unique = true)
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
