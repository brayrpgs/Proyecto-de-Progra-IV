
package cr.ac.una.booleanKitchen.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ConectarDB {

    private final static String databaseName = "db_hotel";
    private final static String user = "root";
    private final static String pass = "";

    private final static int port = 3306;
    private final static String host = "localhost";

    private final static String url = ""
            + "jdbc:mysql://" + host + ":" + port + "/" + databaseName;
    private static Connection conexion; //java.sql connection variable de librería

    public static Connection conectar() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class for name not found" + e.getMessage());
        }
        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Se estableció conexión con la base de datos");
        } catch (SQLException e) {
            System.out.println("Error de conexión" + e.getMessage());
        }
        return conexion;
    }

}
