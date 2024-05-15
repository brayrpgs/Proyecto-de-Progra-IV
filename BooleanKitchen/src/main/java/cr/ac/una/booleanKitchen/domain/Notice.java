/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import java.time.LocalDate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author kenda
 */

@Setter
@Getter
@ToString
public class Notice {
    
    
    //Atributos de la clase
    private String id;
    private String tittle;
    private String resume;
    private LocalDate date;
    private String author;
    private Boolean status;
    private String url;
    private String image; 
    
    //Constructores

    public Notice() {}

    public Notice(String id, String tittle, String resume, LocalDate date, String author, Boolean status, String url, String image) {
        
        this.id = id;
        this.tittle = tittle;
        this.resume = resume;
        this.date = date;
        this.author = author;
        this.status = status;
        this.url = url;
        this.image = image;
    }
    
}
