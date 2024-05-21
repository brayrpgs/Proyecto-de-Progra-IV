package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Ingredient;
import cr.ac.una.booleanKitchen.Data.IngredientData;
import cr.ac.una.booleanKitchen.domain.Category;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author kenda
 */


@Service
public class IngredientService {

    public static LinkedList<Ingredient> getIngredients() {

        return new IngredientData().getIngredients();
    }

    public static Ingredient getIngredientByCode(String code) {

        return new IngredientData().getIngredientByCode(code);

    }

    public static Ingredient addIngredient(Ingredient myIngredient) throws SQLException {

        return new IngredientData().addIngredientDB(myIngredient);

    }

    public static void deleteIngredient(String IdToDelete) throws SQLException {

        new IngredientData().deleteIngredientFromDB(IdToDelete);

    }

    public static boolean duplicatedIdIngredient(Ingredient myIngredient) throws SQLException {

        return new IngredientData().duplicatedIdIngredient(myIngredient);

    }

    public static boolean duplicatedNameIngredient(Ingredient myIngredient) throws SQLException {

        return new IngredientData().duplicatedNameIngredient(myIngredient);

    }

    public static boolean UpdateIngredient(Ingredient myIngredient) throws SQLException {

        return new IngredientData().updateIngredient(myIngredient);

    }

    public static LinkedList<Category> getCategory() throws SQLException {

        return new IngredientData().getCategory();

    }

    // Apartado de imagenes

    public String date(LocalDateTime localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
        return localDate.format(formatter);
    }

    public String uploadImage(MultipartFile image) {

        String date = date(LocalDateTime.now());

        if (!image.isEmpty()) {
            try {
                // Obtener los bytes de la imagen
                //byte[] bytes = image.getBytes();

                // Ruta del directorio de destino
                //String uploadDir = "src/main/resources/static/img/ingredientes/";

                // Ruta del archivo de destino
                //String filePath = uploadDir + date + image.getOriginalFilename();

                // Escribir los bytes en el archivo de destino
                //Path path = Paths.get(filePath);
                //Files.write(path, bytes);

                String nameFinal = date + image.getOriginalFilename();

                Path path = Path.of("BooleanKitchen","src" , "main", "resources", 
                "static" , "imgIng" ,nameFinal);   
                
                Files.createFile(path);
                Files.write(path, image.getBytes());

                return nameFinal;

            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
                
            }
        }
        return "";
    }

    public boolean deleteImage(String imageName) {
        
        try {
            Path path = Path.of("BooleanKitchen","src" , "main", "resources", 
                "static" , "imgIng", imageName);  
            Files.deleteIfExists(path);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores (puedes cambiarlo según tus necesidades)
            return false;
        }
    }
}