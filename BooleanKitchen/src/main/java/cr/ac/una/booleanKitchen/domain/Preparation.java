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
    private String idSerial;
    private List<String> steps;
    private String difficulty;
    private LocalTime time;
    private Image img;
    private String alternative; //alternativa de ingredientes
    private String warnings;//advertencias sobre acciones con cosas peligrosas
    private int step; //pasos
    private String authorNote;

    //Constructores
    public Preparation() {
    }

    public Preparation(int id, String idSerial, List<String> steps, String difficulty, LocalTime time, Image img, String alternative, String warnings, int step, String authorNote) {
        this.id = id;
        this.idSerial = idSerial;
        this.steps = steps;
        this.difficulty = difficulty;
        this.time = time;
        this.img = img;
        this.alternative = alternative;
        this.warnings = warnings;
        this.step = step;
        this.authorNote = authorNote;
    }

    

    

    
    
    
    
}
