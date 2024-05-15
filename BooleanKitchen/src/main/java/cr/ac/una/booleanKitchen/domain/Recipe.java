
package cr.ac.una.booleanKitchen.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Daniel Briones
 */
@Setter
@Getter
@ToString
@Entity
@Table(name="tb_bk_recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",nullable = false)
    private int id;
    @Column(name="IDENTIFICADOR",nullable = false,unique = true)
    private String identificador;
    @Column(name="NOMBRE")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_ORIGEN", referencedColumnName = "ID" )
    private Origin origin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_CATEGORIA", referencedColumnName = "ID" )
    private Category category;
    @Column(name="CALIFICACION")
    private int calificacion;
    @Column(name="RUTA_IMG")
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_USUARIO", referencedColumnName = "ID" )
    private User user;
    @Column(name="TOTAL_CALIFICACION")
    private int totalCali;
    
    @OneToMany(mappedBy = "idRecipeSelected", cascade = CascadeType.ALL)
    private List<Comment> comentarios;
    
    public Recipe() {
    }

    public Recipe(int id, String identificador, String name, Origin origin, Category category, int calificacion, String urlImagen, User user, int totalCali) {
        this.id = id;
        this.identificador = identificador;
        this.name = name;
        this.origin = origin;
        this.category = category;
        this.calificacion = calificacion;
        this.image = urlImagen;
        this.user = user;
        this.totalCali = totalCali;
    }

    
}
