/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Utilidades;

import cr.ac.una.booleanKitchen.domain.Preparation;
import cr.ac.una.booleanKitchen.domain.Step;
import cr.ac.una.booleanKitchen.domain.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author Usuario
 */
public class Utilidades {
    public static final User user = new User(1, LocalDate.now(), "USE-001", "Daniel Briones","ejemplo@gmail.com", "1234", "default_profile", "admin");
    //para preparacion
     public static List<Step> stepsLis = new ArrayList();
     public static int index;
     
     public static Preparation preparacion = new Preparation();
     
     static {
        stepsLis = new LinkedList<>();
    }

}


