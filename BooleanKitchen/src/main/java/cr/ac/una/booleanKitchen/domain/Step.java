/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

/**
 *
 * @author Usuario
 */
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
public class Step {
   private int id;
    private Preparation prep;
    private String title;
    private String routeImg;
    private String textStep;

    public Step() {
    }

    public Step(int id, Preparation prep, String title, String routeImg, String textStep) {
        this.id = id;
        this.prep = prep;
        this.title = title;
        this.routeImg = routeImg;
        this.textStep = textStep;
    }   
}
