/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;
@ToString
@Getter
@Setter
@Entity
@Table(name = "tb_bk_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @Column(name = "NOMBRE_USUARIO", unique = true)
    private String userName;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "TIPO_USUARIO")
    private String userType;
    @Column(name = "RUTA_IMG")
    private String imagenFileName;
    @Column(name = "IDENTIFICADOR", unique = true)
    private String userCode;
    @Column(name = "CREACION_AT", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    /**
     * @param id
     * @param userName
     * @param email
     * @param password
     * @param userType
     * @param imagenFileName
     * @param userCode
     * @param createdAt
     */
    public User(Integer id, String userName, String email, String password, String userType, String imagenFileName,
            String userCode, Date createdAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.imagenFileName = imagenFileName;
        this.userCode = userCode;
        this.createdAt = createdAt;
    }

    public User() {
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagenFileName() {
        return imagenFileName;
    }

    public void setImagenFileName(String imagenFileName) {
        this.imagenFileName = imagenFileName;
    }

    @PrePersist
    protected void onPrePersist() {
        onCreate();
        onUserCodeGeneration();
    }

    protected void onCreate() {
        createdAt = new Date();
    }

    protected void onUserCodeGeneration() {
        userCode = UUID.randomUUID().toString().substring(0, 8);
    }
}
