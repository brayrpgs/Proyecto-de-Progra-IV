/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Ceasar Calvo ///Fecha de modificacion 14/03/2024
 */
@Setter
@Getter
@ToString
@Entity
@Table(name="tb_bk_origin")
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",nullable = false)
    private int id;
    @Column(name="NOMBRE",nullable = false,unique = true)
    private String name;
    @Column(name="DESCRIPCION")
    private String description;
    @Column(name="IDENTIFICADOR",nullable = false,unique = true)
    private String identificador;
    @Column(name="PAIS")
    private String country;
    @Column(name="RUTA_IMG")
    private String idImg;
    @Column(name="CONTINENTE")
    private String continent;
    @Column(name="SABOR")
    private String taste;

    public Origin() {
    }

    public Origin(int id, String name, String description, String identificador, String country, String idImg, String continent, String taste) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.identificador = identificador;
        this.country = country;
        this.idImg = idImg;
        this.continent = continent;
        this.taste = taste;
    }
    

}
