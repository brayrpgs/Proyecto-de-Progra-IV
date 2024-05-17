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
    //Usuarios normales
    private Integer id; // Agregado para identificar al usuario durante la edición

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String userName;
	
    @NotBlank(message = "El email es un campo requerido")
    private String email;
    //@NotBlank(message = "Ingrese una contraseña")
    private String password;
    
    @NotBlank(message = "Seleccione el tipo de usuario")
    private String userType;
    
    private String userCode;
    private Date createdAt;
    private String imagenFileName; // Agregado para almacenar el nombre del archivo de imagen

    private MultipartFile imageFile;
    
    //Administradores
    //@NotBlank(message = "Por favor ingrese su nombre")
    private String nombre;
    //@NotBlank(message = "Campo requerido")
    private String apellido;
    //@NotBlank(message = "Campo requerido")
    private String carnet;

    public UserDto(Integer id, String userName, String email, String password, String userType, String userCode, Date createdAt, String imagenFileName, MultipartFile imageFile, String nombre, String apellido, String carnet) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.userCode = userCode;
        this.createdAt = createdAt;
        this.imagenFileName = imagenFileName;
        this.imageFile = imageFile;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carnet = carnet;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    
        
        
}
