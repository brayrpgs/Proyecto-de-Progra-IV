
package cr.ac.una.booleanKitchen.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConectarDB {

    public static Connection conectar() {

        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1/dbbooleankitchen", "root", "");
            System.out.println("Se estableció conexión con la base de datos");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error de conexión" + e.getMessage());
        }
        return null;
    }

}
