package cr.ac.una.booleanKitchen.domain;

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
public class ShopList {

    private Integer id;
    private String name;
    private Float amount;
    private String notes;
    private String brand;
    private Boolean state;
    private Integer idUser;

    public ShopList(Integer id, String name, Float amount, String notes, String brand, Boolean state, Integer idUser) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.notes = notes;
        this.brand = brand;
        this.state = state;
        this.idUser = idUser;
    }

    public ShopList() {
    }

}
