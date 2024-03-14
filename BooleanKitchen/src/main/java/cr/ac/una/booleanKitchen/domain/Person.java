/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author BrayRPGs
 */
@Setter
@Getter
@ToString
public class Person {
    private String nationality;
    private String phoneNumber;
    //comentario
    public Person(String nationality, String phoneNumber) {
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
    }
    public Person() {
    }
}
