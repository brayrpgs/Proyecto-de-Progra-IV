package cr.ac.una.booleanKitchen.domain;

import jakarta.persistence.Column;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.ToString;

@ToString

@Entity
@Table(name = "tb_bk_notice", schema = "public")
public class Notice {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String IDENTIFICADOR;
    private String TITULO;
    private String RESUMEN;
    private Date FECHA;
    @Column(name = "AUTOR")
    private String AUTHOR;
    private Boolean ESTADO;
    private String URL;
    private String RUTA_IMG;


    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getIDENTIFICADOR() {
        return this.IDENTIFICADOR;
    }

    public void setIDENTIFICADOR(String IDENTIFICADOR) {
        this.IDENTIFICADOR = IDENTIFICADOR;
    }

    public String getTITULO() {
        return this.TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public String getRESUMEN() {
        return this.RESUMEN;
    }

    public void setRESUMEN(String RESUMEN) {
        this.RESUMEN = RESUMEN;
    }

    public Date getFECHA() {
        return this.FECHA;
    }

    public void setFECHA(Date FECHA) {
        this.FECHA = FECHA;
    }

    public String getAUTHOR() {
        return this.AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public Boolean isESTADO() {
        return this.ESTADO;
    }

    public Boolean getESTADO() {
        return this.ESTADO;
    }

    public void setESTADO(Boolean ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getRUTA_IMG() {
        return this.RUTA_IMG;
    }

    public void setRUTA_IMG(String RUTA_IMG) {
        this.RUTA_IMG = RUTA_IMG;
    }


}
