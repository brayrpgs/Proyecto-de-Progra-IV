/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import java.time.LocalTime;
import java.util.List;
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
public class Preparation {
    //Atributos
    private int id;
    private List<String> steps;
    private String difficulty;
    private LocalTime time;
    //private Imagen img;
    private int portion;
    private String authorNote;

    //Constructores
    public Preparation() {
    }

    public Preparation(int id, List<String> steps, String difficulty, LocalTime time, int portion, String authorNote) {
        this.id = id;
        this.steps = steps;
        this.difficulty = difficulty;
        this.time = time;
        this.portion = portion;
        this.authorNote = authorNote;
    }
    
    
    
}
