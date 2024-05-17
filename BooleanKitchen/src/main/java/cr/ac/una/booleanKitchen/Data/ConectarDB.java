
package cr.ac.una.booleanKitchen.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConectarDB {

    private final static String databaseName = "dbbooleankitchen";
    private final static String user = "root";
    private final static String pass = "";

    private final static int port = 3306;
    private final static String host = "localhost";

    private final static String url = ""
            + "jdbc:mysql://" + host + ":" + port + "/" + databaseName;
    private final static String url2="";
    private static Connection conexion; //java.sql connection variable de librería

    public static Connection conectar() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class for name not found" + e.getMessage());
        }
        try {
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            try {
                conexion = DriverManager.getConnection(url2, user, pass);
            } catch (SQLException ex) {
                Logger.getLogger(ConectarDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conexion;
    }

}
