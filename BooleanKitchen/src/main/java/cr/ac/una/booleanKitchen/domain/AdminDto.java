/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

/**
 *
 * @author Maik
 */
public class AdminDto {
    
    private Integer id;  // ID del Admin
    private Integer userId;  // ID del usuario asociado, importante para vincular a User

    private String nombre;
    private String apellido;
    private String carne;  // Carnet del administrador

    // Constructores, getters y setters
    public AdminDto() {
    }

    public AdminDto(Integer id, Integer userId, String nombre, String apellido, String carne) {
        this.id = id;
        this.userId = userId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carne = carne;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }
    
    
}


