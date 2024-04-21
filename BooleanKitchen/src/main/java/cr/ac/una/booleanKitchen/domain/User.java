/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Maik
 */
@Setter
@Getter
public class User extends Person{
    private int id;
    private String username;
    private String email;
    private String password;
    private String userType;
   //private Image image;

    public User() {
    }

    public User(int id, String username, String email, String password, String userType, String nationality, String phoneNumber) {
        super(nationality, phoneNumber);
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

}