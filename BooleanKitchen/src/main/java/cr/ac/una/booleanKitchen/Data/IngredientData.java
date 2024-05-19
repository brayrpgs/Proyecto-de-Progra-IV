package cr.ac.una.booleanKitchen.Data;

import cr.ac.una.booleanKitchen.domain.Ingredient;
import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.domain.ConectarDB;
import static cr.ac.una.booleanKitchen.domain.ConectarDB.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;



public class IngredientData extends ConectarDB {

    // String estatico de la base de datos
    private static final String TBINGREDIENT = "tb_bk_ingredient";
    private static final String TBCATEGORY = "tb_bk_category";

    // Obtener las categorias disponibles
    public LinkedList<Category> getCategory() {

        LinkedList<Category> categories = new LinkedList<>();
        String query = "SELECT * FROM " + TBCATEGORY + " WHERE ETIQUETA = ? ORDER BY NOMBRE";
        Category category;
        try {
            Connection connection = conectar();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "INGREDIENTE");
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                category = new Category();
                category.setId(result.getInt("ID")); 
                category.setName(result.getString("NOMBRE"));

                categories.add(category); 
            }

            // Cerrar la conexión y liberar recursos
            connection.close();
            statement.close();
            result.close();

        } catch (SQLException e) {
            return null;
        }

        return categories; 
    }

    // Metodo que obtiene todos los ingredientes que hayan en la base de datos
    public LinkedList<Ingredient> getIngredients() {

        LinkedList<Ingredient> ingredients = new LinkedList<>();
        String query = "SELECT "
                + "I.*, "
                + "C.NOMBRE AS NOMBRE_CATEGORIA "
                + "FROM "
                + "tb_bk_ingredient AS I INNER JOIN tb_bk_category AS C "
                + "ON "
                + "I.ID_CATEGORIA = C.ID;";

        try {
            Connection connection = conectar();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                Ingredient ingredient = new Ingredient();

                ingredient.setId(result.getInt("ID"));
                ingredient.setCode(result.getString("IDENTIFICADOR"));
                ingredient.setName(result.getString("NOMBRE"));
                ingredient.setQuantity(result.getFloat("CANTIDAD"));
                ingredient.setWeight(result.getFloat("PESO"));
                ingredient.setCategory(result.getInt("ID_CATEGORIA"));
                ingredient.setCategoryName(result.getString("NOMBRE_CATEGORIA"));
                ingredient.setCalories(result.getFloat("CALORIAS"));
                ingredient.setDescription(result.getString("DESCRIPCION"));
                ingredient.setImage(result.getString("RUTA_IMG"));

                ingredients.add(ingredient);
            }

            return ingredients;

        } catch (SQLException e) {

            return null;
        }
    }

    public Ingredient getIngredientByCode(String code) {
        String query = "SELECT * FROM " + TBINGREDIENT + " WHERE IDENTIFICADOR = ?";

        try {
            Connection connection = conectar();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, code);
            ResultSet result = statement.executeQuery();

            if (result.next()) {

                Ingredient ingredient = new Ingredient();

                ingredient.setId(result.getInt("ID"));
                ingredient.setCode(result.getString("IDENTIFICADOR"));
                ingredient.setName(result.getString("NOMBRE"));
                ingredient.setQuantity(result.getFloat("CANTIDAD"));
                ingredient.setWeight(result.getFloat("PESO"));
                ingredient.setCategory(result.getInt("ID_CATEGORIA"));
                ingredient.setCalories(result.getFloat("CALORIAS"));
                ingredient.setDescription(result.getString("DESCRIPCION"));
                ingredient.setImage(result.getString("RUTA_IMG"));

                return ingredient;
            } else {

                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    // Metodo que agrega un ingrediente a la base de datos
    public Ingredient addIngredientDB(Ingredient myIngredient) {

        String sql = "INSERT INTO " + TBINGREDIENT + " (" + "ID" + ",IDENTIFICADOR,NOMBRE,CANTIDAD,"
                + "PESO,ID_CATEGORIA,CALORIAS,DESCRIPCION,RUTA_IMG) VALUES(?,?,?,?,?,?,?,?,?);";

        try (Connection connection = conectar(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, myIngredient.getId());
            statement.setString(2, myIngredient.getCode());
            statement.setString(3, myIngredient.getName());
            statement.setFloat(4, myIngredient.getQuantity());
            statement.setFloat(5, myIngredient.getWeight());
            statement.setInt(6, myIngredient.getCategory());
            statement.setFloat(7, myIngredient.getCalories());
            statement.setString(8, myIngredient.getDescription());
            statement.setString(9, myIngredient.getImage());

            statement.executeUpdate();

            return myIngredient;

        } catch (SQLException e) {

            return null;
        }
    }

    // Metodo que elimina un ingrediente de la base de datos
    public void deleteIngredientFromDB(String IdToDelete) {

        String sql = "DELETE FROM " + TBINGREDIENT + " WHERE IDENTIFICADOR = ?;";

        try (Connection connection = conectar(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, IdToDelete);
            statement.executeUpdate();

        } catch (SQLException e) {

        }
    }

    // Metodo para saber si hay algun ingrediente que tenga un codigo igual
    // Para omitirlo y no dejar que hayan dos ingredientes con el mismo codigo.
    public boolean duplicatedIdIngredient(Ingredient myIngredient) {

        LinkedList<Ingredient> ingredients = getIngredients();

        for (Ingredient data : ingredients) {

            if (myIngredient.getCode().equals(data.getCode())) {
                return true;
            }
        }

        return false;
    }

    public boolean duplicatedNameIngredient(Ingredient myIngredient) {

        LinkedList<Ingredient> ingredients = getIngredients();

        for (Ingredient data : ingredients) {

            if (myIngredient.getName().equalsIgnoreCase(data.getName())) {
                return true;
            }
        }

        return false;
    }

    // Metodo para actualizar un ingrediente en la base de datos
    public boolean updateIngredient(Ingredient myIngredient) {

        try {

            String sql = "UPDATE " + TBINGREDIENT + " SET NOMBRE = ?,"
                    + " CANTIDAD = ?, PESO = ?, ID_CATEGORIA = ?, CALORIAS = ?, "
                    + "DESCRIPCION = ?, RUTA_IMG = ? WHERE IDENTIFICADOR = ?";
            Connection conexion = conectar();

            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1, myIngredient.getName());
                statement.setFloat(2, myIngredient.getQuantity());
                statement.setFloat(3, myIngredient.getWeight());
                statement.setInt(4, myIngredient.getCategory());
                statement.setFloat(5, myIngredient.getCalories());
                statement.setString(6, myIngredient.getDescription());
                statement.setString(7, myIngredient.getImage());
                statement.setString(8, myIngredient.getCode());

                statement.executeUpdate();
            }
            return true;

        } catch (SQLException ex) {

            if (ex.getErrorCode() == 1062) {
                if (ex.getMessage().contains("NOMBRE")) {
                    return false;
                }

            }
            return false;
        }
    }

}