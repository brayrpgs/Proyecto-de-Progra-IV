/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

/**
 *
 * @author Usuario
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author josue
 */
@Getter
@Setter
@ToString
@Entity
@Table(name="tb_bk_preparation_list")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ID_PREPARACION",referencedColumnName = "ID")
    private Preparation preparation;
    @Column(name="TITULO_PASO")
    private String title;
    @Column(name="RUTA_IMG")
    private String routeImg;
    @Column(name="PASO")
    private String textStep;

    public Step() {
    }

    public Step(int id, Preparation prep, String title, String routeImg, String textStep) {
        this.id = id;
        this.preparation = prep;
        this.title = title;
        this.routeImg = routeImg;
        this.textStep = textStep;
    }   
}
