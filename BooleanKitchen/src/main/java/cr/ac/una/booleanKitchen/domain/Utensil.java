/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
 * @author Ceasar Calvo ///Fecha de modificacion 14/03/2024
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "tb_bk_utensil")//Nombre de la tabla en mySQL PHPmyAdmin
public class Utensil {
    
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Llave Primaria en BD (No editable)
    @Column(name = "ID", nullable = false)
    private int id;

    //Atributos editables en BD
    //Identeificador
    @Column(name = "IDENTIFICADOR", nullable = false, unique = true)//Unico no repetible
    private String identificador;

    //Nombre
    @Column(name = "NOMBRE", nullable = false, unique = true)//Unico no repetible
    private String nombre;

    //Material
    @Column(name = "MATERIAL")
    private String material;

    //ID-CATEGORIA
    @ManyToOne(fetch=FetchType.LAZY)//Cardinalidad entre tablas (preconsulta)
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID")//Llave fornea
    private Category categoria;//Objeto

    //Ruta de la imagen
    @Column(name = "RUTA_IMG")
    private String rutaDeImagen;

    //Precio
    @Column(name = "PRECIO")
    private float precio;

    //Cantidad
    @Column(name = "CANTIDAD")
    private int cantidad;

    //Descripción
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public Utensil() {
    }

    public Utensil(int id, String identificador, String nombre, String material, Category categoria, String rutaDeImagen, float precio, int cantidad, String descripcion) {
        this.id = id;
        this.identificador = identificador;
        this.nombre = nombre;
        this.material = material;
        this.categoria = categoria;
        this.rutaDeImagen = rutaDeImagen;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

   
}
