/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.data;

import cr.ac.una.booleanKitchen.domain.Origin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ceasar
 */
public class DataOrigin extends ConectarDB{
    private static final String TBORIGIN = "tb_bk_origin";
    private static final String ID = "ID";


       

  public boolean createOrigin(Origin origin) throws SQLException {
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
        // Verificar si ya existe un registro con el mismo nombre o ID de origen
        conexion = conectar();
        String checkIfExistsQuery = "SELECT COUNT(*) FROM " + TBORIGIN + " WHERE NOMBRE = ? OR IDENTIFICADOR = ?";
        statement = conexion.prepareStatement(checkIfExistsQuery);
        statement.setString(1, origin.getName());
        statement.setString(2, origin.getIdentificador());
        resultSet = statement.executeQuery();
        if (resultSet.next() && resultSet.getInt(1) > 0) {//SI HAY UN ID O NOMBRE IGUAL NO CREA
            return false;
        }
        
        // Si no existe, procedemos a insertar el nuevo origen
        String sql = "INSERT INTO " + TBORIGIN + " (" + ID + ",IDENTIFICADOR,NOMBRE,DESCRIPCION,"
        + "PAIS,RUTA_IMG,CONTINENTE,SABOR) VALUES (?,?,?,?,?,?,?,?)";
        
        System.out.println(origin.getCountry());
        statement = conexion.prepareStatement(sql);
        statement.setInt(1, origin.getId());
        statement.setString(2, origin.getIdentificador());
        statement.setString(3, origin.getName());
        statement.setString(4, origin.getDescription());
        statement.setString(5, origin.getCountry());
        statement.setString(6, origin.getIdImg());
        statement.setString(7, origin.getContinent());
        statement.setString(8, origin.getTaste());
        
        int resultado = statement.executeUpdate();
        return (resultado > 0);
    } catch (SQLException e) {
        //MENSAJE DE ERROR
        e.getErrorCode(); // 
        return false;
    }
  }
    public LinkedList<Origin> getListOfOrigin() throws SQLException{
        LinkedList<Origin> origins = new LinkedList<>();
        String sql = "SELECT * FROM "+TBORIGIN+";";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Origin org;
        while(result.next()){
            org = new Origin();
            org.setId(result.getInt(ID));
            org.setName(result.getString("NOMBRE"));
            org.setDescription(result.getString("DESCRIPCION"));
            org.setIdentificador(result.getString("IDENTIFICADOR"));
            org.setCountry(result.getString("PAIS"));
            org.setIdImg(result.getString("RUTA_IMG"));
            org.setContinent(result.getString("CONTINENTE"));
            org.setTaste(result.getString("SABOR"));
        origins.add(org);
        }
        return origins;
    }
    
    public Origin obtenerOrigen(String id) {

        // String sql="";
        Connection cn = conectar();
        ResultSet result;
       Origin org = new Origin();
        //Imagen img;
        try {
            PreparedStatement sentencia = cn.prepareStatement("SELECT * FROM "+TBORIGIN+" WHERE IDENTIFICADOR = ?;");
            sentencia.setString(1, id);
            result = sentencia.executeQuery();

            if (result.next()) {
            
            org.setId(result.getInt(ID));
            org.setName(result.getString("NOMBRE"));
            org.setDescription(result.getString("DESCRIPCION"));
            org.setIdentificador(result.getString("IDENTIFICADOR"));
            org.setCountry(result.getString("PAIS"));
            org.setIdImg(result.getString("RUTA_IMG"));
            org.setContinent(result.getString("CONTINENTE"));
            org.setTaste(result.getString("SABOR"));


                sentencia.close();
                cn.close();
                return org;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataOrigin.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return org;
    }
    
    
       public boolean updateOrigin(Origin originFromIndex) {

        try {
            String sql = "UPDATE "+TBORIGIN+" SET NOMBRE = ?,DESCRIPCION=?,PAIS=?,RUTA_IMG=?,CONTINENTE=?,SABOR=? WHERE IDENTIFICADOR = ?";
            Connection conexion = conectar();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, originFromIndex.getName());
            statement.setString(2, originFromIndex.getDescription());
            statement.setString(3, String.valueOf(originFromIndex.getCountry()));
            statement.setString(4, String.valueOf(originFromIndex.getIdImg()));
            statement.setString(5, originFromIndex.getContinent());
            statement.setString(6, originFromIndex.getTaste());
            statement.setString(7,String.valueOf(originFromIndex.getIdentificador()));

            statement.execute();
            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    public boolean deleteOriginFromDB(String IdToDelete) throws SQLException{
        String sql = "DELETE FROM "+TBORIGIN+" WHERE IDENTIFICADOR = ?;";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, IdToDelete);
       int resultado =statement.executeUpdate();
        statement.executeUpdate();
        
        statement.close();
        conexion.close();
        return (resultado>0);
    }
}
