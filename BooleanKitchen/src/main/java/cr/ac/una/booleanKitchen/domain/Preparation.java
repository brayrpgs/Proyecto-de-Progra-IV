/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.LinkedList;
/**
 *
 * @author josue
 */
@Getter
@Setter
@ToString
public class Preparation {
    private int id;
    private int idRecipe;
    private String idSerial;
    private String difficulty;
    private LocalTime time;
    private String routeImg;
    private String noteAuthor;
    private String warnings;
    
    
    private int timeMinute;
    
    private LinkedList<Step> preparationList;
  

    public Preparation() {
    }

    public Preparation(int id, int idRecipe, String idSerial, String difficulty, LocalTime time, String routeImg, String noteAuthor, String warnings, LinkedList<Step> preparationList) {
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
