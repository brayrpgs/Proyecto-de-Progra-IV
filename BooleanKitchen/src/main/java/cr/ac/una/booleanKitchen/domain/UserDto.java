/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.domain;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Maik
 */
public class UserDto {
    
    private Integer id; // Agregado para identificar al usuario durante la edición

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String userName;
	
    @NotBlank(message = "El email es un campo requerido")
    private String email;
	
    @NotBlank(message = "Por favor, agregue una contraseña")
    private String password;
	
    @NotBlank(message = "Seleccione el tipo de usuario")
    private String userType;
    
    private String userCode;
    private Date createdAt;
    private String imagenFileName; // Agregado para almacenar el nombre del archivo de imagen

    private MultipartFile imageFile;

    public UserDto(Integer id, String userName, String email, String password, String userType, String imagenFileName, MultipartFile imageFile) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.imagenFileName = imagenFileName;
        this.imageFile = imageFile;
    }

    public UserDto() {
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getImagenFileName() {
        return imagenFileName;
    }

    public void setImagenFileName(String imagenFileName) {
        this.imagenFileName = imagenFileName;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
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

    
        
        
}
