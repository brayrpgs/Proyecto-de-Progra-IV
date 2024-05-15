
package cr.ac.una.booleanKitchen.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Maik
 */
@Setter
@Getter
@Entity
@Table (name="tb_bk_user")
public class User {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",nullable = false)
    private int id;
    @Column(name="CREACION_AT",nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate creacionAT;
    @Column(name="IDENTIFICADOR",nullable = false,unique = true)
    private String ident;
    @Column(name="NOMBRE_USUARIO",nullable = false,unique = true)
    private String username;
    @Column(name="EMAIL",nullable = false,unique = true)
    private String emai;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="RUTA_IMG")
    private String url;
    @Column(name="TIPO_USUARIO")
    private String userType;

    public User() {
    }

    public User(int id, LocalDate creacionAT, String ident, String username, String emai, String password, String url, String userType) {
        this.id = id;
        this.creacionAT = creacionAT;
        this.ident = ident;
        this.username = username;
        this.emai = emai;
        this.password = password;
        this.url = url;
        this.userType = userType;
    }

}