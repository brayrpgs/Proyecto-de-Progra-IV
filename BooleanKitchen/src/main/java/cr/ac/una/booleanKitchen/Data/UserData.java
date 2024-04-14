/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Data;

import com.Progra.Usuario.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maik
 */
public class UserData {
    
    private static final String TB_USER= "user";
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String IMAGEN_FILE_NAME = "imagen_file_name";
    private static final String PASSWORD = "password";
    private static final String USER_NAME = "user_name";
    private static final String USER_TYPE = "user_type";
    
    public boolean insertarUsuario(User user) {
    String sql = "INSERT INTO " + TB_USER + " (" 
                 + ID + ", " 
                 + USER_NAME + ", " 
                 + EMAIL + ", " 
                 + PASSWORD + ", " 
                 + USER_TYPE + ", " 
                 + IMAGEN_FILE_NAME + ") VALUES (?, ?, ?, ?, ?, ?);";

    try (Connection conexion = ConectarDB.conectar();
         PreparedStatement statement = conexion.prepareStatement(sql)) {

        statement.setInt(1, user.getId());
        statement.setString(2, user.getUserName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getUserType());
        statement.setString(6, user.getImagenFileName());

        int resultado = statement.executeUpdate();
        return resultado > 0; 

    } catch (SQLException e) {
        System.out.println("Error al insertar usuario: " + e.getMessage());
        return false;
    }
  }
    public List<User> obtenerUsuarios() {
    List<User> usuarios = new ArrayList<>();
    String sql = "SELECT " + ID + ", " 
                 + USER_NAME + ", " 
                 + EMAIL + ", " 
                 + PASSWORD + ", " 
                 + USER_TYPE + ", " 
                 + IMAGEN_FILE_NAME + " FROM " + TB_USER;

    try (Connection conexion = ConectarDB.conectar();
         PreparedStatement statement = conexion.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(ID));
            user.setUserName(resultSet.getString(USER_NAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setUserType(resultSet.getString(USER_TYPE));
            user.setImagenFileName(resultSet.getString(IMAGEN_FILE_NAME));
            usuarios.add(user);
        }
    } catch (SQLException e) {
        System.out.println("Error al recuperar usuarios: " + e.getMessage());
    }
    return usuarios;
 }
    public User obtenerUsuarioPorId(int userId) {
    User user = null;
    String sql = "SELECT " + ID + ", "
                 + USER_NAME + ", "
                 + EMAIL + ", "
                 + PASSWORD + ", "
                 + USER_TYPE + ", "
                 + IMAGEN_FILE_NAME + " FROM " + TB_USER + " WHERE " + ID + " = ?";

    try (Connection conexion = ConectarDB.conectar();
         PreparedStatement statement = conexion.prepareStatement(sql)) {
        
        statement.setInt(1, userId); 
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(ID));
                user.setUserName(resultSet.getString(USER_NAME));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setUserType(resultSet.getString(USER_TYPE));
                user.setImagenFileName(resultSet.getString(IMAGEN_FILE_NAME));
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al recuperar el usuario con ID: " + userId + " - " + e.getMessage());
    }
    return user;
 }
  
    public boolean actualizarUsuario(User usuario) throws SQLException {
    String sql = "UPDATE " + TB_USER + " SET " + 
                 USER_NAME + " = ?, " +
                 EMAIL + " = ?, " +
                 PASSWORD + " = ?, " +
                 USER_TYPE + " = ?, " +
                 IMAGEN_FILE_NAME + " = ? " +
                 "WHERE " + ID + " = ?;";

    Connection conexion = ConectarDB.conectar();
    PreparedStatement statement = conexion.prepareStatement(sql);
    try {
        statement.setString(1, usuario.getUserName());
        statement.setString(2, usuario.getEmail());
        statement.setString(3, usuario.getPassword());
        statement.setString(4, usuario.getUserType());
        statement.setString(5, usuario.getImagenFileName());
        statement.setInt(6, usuario.getId());

        int resultado = statement.executeUpdate();
        return resultado > 0; 
    } finally {
        statement.close();
        conexion.close();
    }
}
    public boolean eliminarUsuario(int idUsuario) {
    String sql = "DELETE FROM user WHERE id = ?";
    Connection conexion = null;
    PreparedStatement statement = null;
    try {
        conexion = ConectarDB.conectar(); // Asegúrate que este método gestiona correctamente la conexión.
        statement = conexion.prepareStatement(sql);
        statement.setInt(1, idUsuario);

        int filasEliminadas = statement.executeUpdate();
        return filasEliminadas > 0; // Devuelve true si se eliminó al menos un registro.
    } catch (SQLException e) {
        System.out.println("Error al eliminar el usuario: " + e.getMessage());
        return false;
    } finally {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
  }
}
