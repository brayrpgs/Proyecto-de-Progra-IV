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
import jakarta.persistence.Transient;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Daniel Briones
 */
@Setter
@Getter
@ToString

@Entity
@Table(name = "tb_bk_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "IDENTIFICADOR", unique = true)
    private String identificador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RECETA",referencedColumnName = "ID")
    private Recipe idRecipeSelected;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO",referencedColumnName = "ID")
    private User user;

    @Column(name = "TITULO")
    private String title;
    
    @Column(name = "COMENTARIO")
    private String comment;
    
    @Column(name = "DIFICULTAD")
    private String dificultad;
    
    @Column(name = "CLARIDAD_INST")
    private int claridadInstrucciones;
    
    @Column(name = "MODIFICACION")
    private boolean realizoUnaModificacion;
    
    @Column(name = "SUGERENCIA")
    private String sugerencia;
    
    @Column(name = "RECOMENDACION")
    private boolean recomendariaLaReceta;
    
    @Column(name = "FECHA")
    private LocalDateTime date;
    
    @Column(name = "PUNTUACION")
    private int puntuation;
    
    @Column(name = "REACCIONES")
    private int reaccionesPositivas;
    
    @Transient
    private String antiguedad;
    
    public Comment() {
    }

    public Comment(int id, String identificador, Recipe idRecipeSelected, User user, String title, String comment, String dificultad, int claridadInstrucciones, boolean realizoUnaModificacion, String sugerencia, boolean recomendariaLaReceta, LocalDateTime date, int puntuation, int reaccionesPositivas, String antiguedad) {
        this.id = id;
        this.identificador = identificador;
        this.idRecipeSelected = idRecipeSelected;
        this.user = user;
        this.title = title;
        this.comment = comment;
        this.dificultad = dificultad;
        this.claridadInstrucciones = claridadInstrucciones;
        this.realizoUnaModificacion = realizoUnaModificacion;
        this.sugerencia = sugerencia;
        this.recomendariaLaReceta = recomendariaLaReceta;
        this.date = date;
        this.puntuation = puntuation;
        this.reaccionesPositivas = reaccionesPositivas;
        this.antiguedad = antiguedad;
    }

    

    

    

}
