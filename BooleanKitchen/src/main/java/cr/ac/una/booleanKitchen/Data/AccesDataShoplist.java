package cr.ac.una.booleanKitchen.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import cr.ac.una.booleanKitchen.domain.ShopList;

/**
 *
 * @author BrayRPGs
 */
public class AccesDataShoplist {

    public boolean insert(ShopList data) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_pa", "root", "");
            PreparedStatement ps = cn.prepareStatement(
                    "INSERT INTO shoplist (NAME, AMOUNT, NOTES, BRAND, STATE, IDUSER) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, data.getName());
            ps.setFloat(2, data.getAmount());
            ps.setString(3, data.getNotes());
            ps.setString(4, data.getBrand());
            ps.setBoolean(5, data.getState());
            ps.setInt(6, data.getIdUser());
            // ejecuta la sentencia
            ps.execute();
            ps.close();
            cn.close();
            // retorno
        } catch (SQLException ex) {
            Logger.getLogger(AccesDataShoplist.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public ShopList select(Integer id) {
        ShopList data = new ShopList();
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_pa", "root", "");
            PreparedStatement ps = cn.prepareStatement(
                    "SELECT ID, NAME, AMOUNT, NOTES, BRAND, STATE, IDUSER FROM shoplist WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data = new ShopList(rs.getInt("ID"), rs.getString("NAME"), rs.getFloat("AMOUNT"), rs.getString("NOTES"),
                        rs.getString("BRAND"), rs.getBoolean("STATE"), rs.getInt("IDUSER"));
            }
            rs.close();
            ps.close();
            cn.close();
            // retorno
        } catch (SQLException ex) {
            Logger.getLogger(AccesDataShoplist.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return data;
    }

    public LinkedList<ShopList> showAll() {
        LinkedList<ShopList> list = new LinkedList<>();
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_pa", "root", "");
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM shoplist");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ShopList(rs.getInt("ID"), rs.getString("NAME"), rs.getFloat("AMOUNT"),
                        rs.getString("NOTES"), rs.getString("BRAND"), rs.getBoolean("STATE"), rs.getInt("IDUSER")));
            }
            rs.close();
            ps.close();
            cn.close();
            // retorno
        } catch (SQLException ex) {
            Logger.getLogger(AccesDataShoplist.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
        return list;
    }

    public boolean delete(int id) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_pa", "root", "");
            PreparedStatement ps = cn.prepareStatement("DELETE FROM shoplist WHERE ID = ?");
            ps.setInt(1, id);
            // ejecuta la sentencia
            ps.execute();
            ps.close();
            cn.close();
            // retorno
        } catch (SQLException ex) {
            Logger.getLogger(AccesDataShoplist.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean update(ShopList data) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_pa", "root", "");
            PreparedStatement ps = cn.prepareStatement(
                    "UPDATE shoplist SET NAME = ?, AMOUNT = ?, NOTES = ?, BRAND = ?, STATE = ?,IDUSER = ? WHERE  ID = ?");
            ps.setString(1, data.getName());
            ps.setFloat(2, data.getAmount());
            ps.setString(3, data.getNotes());
            ps.setString(4, data.getBrand());
            ps.setBoolean(5, data.getState());
            ps.setInt(6, data.getIdUser());
            ps.setInt(7, data.getId());
            // ejecuta la sentencia
            ps.execute();
            ps.close();
            cn.close();
            // retorno
        } catch (SQLException ex) {
            Logger.getLogger(AccesDataShoplist.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
