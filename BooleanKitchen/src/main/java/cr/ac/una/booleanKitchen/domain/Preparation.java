/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import java.time.LocalTime;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author josue
 */
@Getter
@Setter
@ToString

@Entity
@Table(name="tb_bk_preparation")
public class Preparation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",nullable = false)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ID_RECETA", referencedColumnName = "ID" )
    private Recipe idRecipe;
    @Column(name="IDENTIFICADOR",nullable = false,unique = true)
    private String idSerial;
    @Column(name="DIFICULTAD")
    private String difficulty;
    @Column(name="TIEMPO",nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime time;
    @Column(name="RUTA_IMG")
    private String routeImg;
    @Column(name="NOTA_AUTOR")
    private String noteAuthor;
    @Column(name="ALERTA")
    private String warnings;
    
    @Transient
    private String timeStr;
    
    @OneToMany(mappedBy = "preparation", cascade = CascadeType.ALL)
    private List<Step> preparationList= new ArrayList<>();
  

    public Preparation() {
        this.id = 0;
    }

    public Preparation(int id, Recipe idRecipe, String idSerial, String difficulty, LocalTime time, String routeImg, String noteAuthor, String warnings, LinkedList<Step> preparationList) {
        this.id = id;
        this.idRecipe = idRecipe;
        this.idSerial = idSerial;
        this.difficulty = difficulty;
        this.time = time;
        this.routeImg = routeImg;
        this.noteAuthor = noteAuthor;
        this.warnings = warnings;
        this.preparationList = preparationList;
    }

    
}
