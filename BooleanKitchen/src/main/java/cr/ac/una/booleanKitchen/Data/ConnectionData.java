package cr.ac.una.booleanKitchen.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionData {

    /**
     * @param db nombre de la base de datos
     * @return
     */
    Connection getConnection(String db) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, "root", "");
            return cn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
