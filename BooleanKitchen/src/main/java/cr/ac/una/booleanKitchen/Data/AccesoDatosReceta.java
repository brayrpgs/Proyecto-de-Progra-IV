///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package cr.ac.una.booleanKitchen.Data;
//
//import cr.ac.una.booleanKitchen.domain.Category;
//import cr.ac.una.booleanKitchen.domain.ConectarDB;
//import cr.ac.una.booleanKitchen.domain.Ingredient;
//import cr.ac.una.booleanKitchen.domain.Recipe;
//import cr.ac.una.booleanKitchen.domain.Utensil;
//import cr.ac.una.booleanKitchen.domain.Notice;
//import cr.ac.una.booleanKitchen.domain.Origin;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Base64;
//import java.util.LinkedList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Usuario
// */
//public class AccesoDatosReceta extends ConectarDB {
//
//    public LinkedList<Recipe> mejoresRecetas() {
//        LinkedList<Recipe> lista = new LinkedList<>();
//
//        try {
//            Connection cn = conectar();
//            PreparedStatement sentencia = (PreparedStatement) cn.prepareStatement("SELECT IDENTIFICADOR, NOMBRE, RUTA_IMG FROM tb_bk_recipe ORDER BY CALIFICACION DESC LIMIT 10");
//
//            ResultSet rs = sentencia.executeQuery();
//
//            while (rs.next()) {
//                Recipe r = new Recipe();
//                r.setIdentificador(rs.getString("IDENTIFICADOR"));
//                r.setName(rs.getString("NOMBRE"));
////                r.setOrigin(rs.getInt("ORIGIN"));
//                Category cat = new Category();
//                cat.setName("Categoria BD");
//                r.setCategory(cat);
////                r.setImage(rs.getString("RUTA_IMG"));
//                lista.add(r);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        System.out.println("Tamaño de la lista de mejores: " + lista.size());
//        return lista;
//    }
//
//    public LinkedList<Recipe> todasLasRecetas() {
//        LinkedList<Recipe> lista = new LinkedList<>();
//
//        try {
//            Connection cn = conectar();
//            PreparedStatement sentencia = (PreparedStatement) cn.prepareStatement("SELECT IDENTIFICADOR, NOMBRE, RUTA_IMG FROM tb_bk_recipe");
//
//            ResultSet rs = sentencia.executeQuery();
//
//            while (rs.next()) {
//                Recipe r = new Recipe();
//                r.setIdentificador(rs.getString("IDENTIFICADOR"));
//                r.setName(rs.getString("NOMBRE"));
////                r.setOrigin(rs.getInt("ORIGIN"));
//                Category cat = new Category();
//                cat.setName("Categoria BD");
//                r.setCategory(cat);
////                r.setImage(rs.getString("RUTA_IMG"));
//                lista.add(r);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return lista;
//    }
//
//    public boolean agregarReceta(Recipe recipe) {
//
//        Connection cn = null;
//        PreparedStatement sentencia = null;
//        boolean salida = false;
//        try {
//            cn = conectar();
//            String sql = """
//                         INSERT INTO `tb_bk_recipe`
//                            (`IDENTIFICADOR`, `NOMBRE`, `ORIGIN`, `ID_CATEGORY`, `CALIFICACION`, `RUTA_IMG`, `ID_USER`) 
//                         VALUES (?,?,?,?,?,?,?);
//                         """;
//            sentencia = (PreparedStatement) cn.prepareStatement(sql);
//            sentencia.setString(1, recipe.getIdentificador());
//            sentencia.setString(2, recipe.getName());
////            sentencia.setInt(3, recipe.getOrigin());
//            sentencia.setInt(4, recipe.getCategory().getId());
//            sentencia.setInt(5, 0);
////            sentencia.setString(6, recipe.getImage().replace(":", "-"));
//            sentencia.setInt(7, 1);
//            
//            if(sentencia.executeUpdate() >= 1){
//                salida = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//            try {
//                if (cn != null) {
//                    cn.close();
//                }
//                
//                if(sentencia != null){
//                    sentencia.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(AccesoDatosReceta.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        return salida;
//    }
//
//    public Recipe verDetallesReceta(String identificador) {
//        Recipe r = new Recipe();
//
//        return r;
//    }
//
//    public boolean editarReceta(Recipe recipe) {
//        return true;
//    }
//
//    public boolean eliminarReceta(String identificador) {
//        return true;
//    }
//
//    public LinkedList<Recipe> obtenerTodasLasRecetas() {
//        LinkedList<Recipe> lista = new LinkedList<>();
//
//        return lista;
//    }
//
////    Borrar de aqui para abajo
//    public LinkedList<Category> categorias() {
//        LinkedList<Category> lista = new LinkedList<>();
//        try {
//            Connection cn = conectar();
//            PreparedStatement sentencia = (PreparedStatement) cn.prepareStatement("SELECT * FROM `tb_bk_category`");
//
//            ResultSet rs = sentencia.executeQuery();
//
//            while (rs.next()) {
//                Category c = new Category();
//                c.setIdSerial(rs.getString("IDENTIFICADOR"));
//                c.setName(rs.getString("NOMBRE"));
//                c.setDescription(rs.getString("DESCRIPCION"));
//                c.setLabel(rs.getString("ETIQUETA"));
//                c.setQuantity(rs.getInt("CANTIDAD"));
//                c.setImage(rs.getString("RUTA_IMG"));
//                lista.add(c);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return lista;
//    }
//
//    public LinkedList<Utensil> utensilios() {
//        LinkedList<Utensil> lista = new LinkedList<>();
//        try {
//            Connection cn = conectar();
//            PreparedStatement sentencia = (PreparedStatement) cn.prepareStatement("SELECT * FROM `tb_bk_utensil`");
//
//            ResultSet rs = sentencia.executeQuery();
//
//            while (rs.next()) {
//                Utensil u = new Utensil();
//                u.setIdentificador(rs.getString("IDENTIFICADOR"));
//                u.setName(rs.getString("NOMBRE"));
//                lista.add(u);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return lista;
//    }
//
//    public LinkedList<Ingredient> ingredientes() {
//        LinkedList<Ingredient> lista = new LinkedList<>();
//        try {
//            Connection cn = conectar();
//            PreparedStatement sentencia = (PreparedStatement) cn.prepareStatement("SELECT * FROM `tb_bk_ingredient`");
//
//            ResultSet rs = sentencia.executeQuery();
//
//            while (rs.next()) {
//                Ingredient i = new Ingredient();
//                i.setIdentificador(rs.getString("IDENTIFICADOR"));
//                i.setName(rs.getString("NOMBRE"));
//                lista.add(i);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return lista;
//    }
//
//    public LinkedList<Notice> noticias() {
//        LinkedList<Notice> lista = new LinkedList<>();
//        try {
//            Connection cn = conectar();
//            PreparedStatement sentencia = (PreparedStatement) cn.prepareStatement("SELECT * FROM `tb_bk_notice`");
//
//            ResultSet rs = sentencia.executeQuery();
//
//            while (rs.next()) {
//                Notice noti = new Notice();
//                noti.setTittle(rs.getString("TITULO"));
//                noti.setResume("RESUMEN");
//                noti.setAuthor(rs.getString("AUTHOR"));
//                noti.setImage(rs.getString("RUTA_IMG"));
//                lista.add(noti);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return lista;
//    }
//
//    public LinkedList<Origin> origen() {
//        LinkedList<Origin> lista = new LinkedList<>();
//        try {
//            Connection cn = conectar();
//            PreparedStatement sentencia = (PreparedStatement) cn.prepareStatement("SELECT * FROM `tb_bk_origin`");
//
//            ResultSet rs = sentencia.executeQuery();
//
//            while (rs.next()) {
//                Origin ori = new Origin();
//                ori.setIdentificador(rs.getString("IDENTIFICADOR"));
//                ori.setName(rs.getString("NOMBRE"));
//                ori.setImg(rs.getString("RUTA_IMG"));
//                lista.add(ori);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return lista;
//    }
//
//}
