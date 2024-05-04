/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


import org.springframework.web.multipart.MultipartFile;

import cr.ac.una.booleanKitchen.Data.IngredientData;
import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.domain.Ingredient;

/**
 *
 * @author kenda
 */
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
                byte[] bytes = image.getBytes();

                // Ruta del directorio de destino
                String uploadDir = "Proyecto-de-Progra-IV/BooleanKitchen/src/main/resources/static/img/ingredientes/";

                // Ruta del archivo de destino
                String filePath = uploadDir + date + image.getOriginalFilename();

                // Escribir los bytes en el archivo de destino
                Path path = Paths.get(filePath);
                Files.write(path, bytes);

                return date + image.getOriginalFilename();

            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
                
            }
        }
        return "";
    }

    public boolean deleteImage(String imageName) {
        
        String directory = "Proyecto-de-Progra-IV/BooleanKitchen/src/main/resources/static/img/ingredientes/";
        try {
            Path imagePath = Paths.get(directory, imageName);
            Files.deleteIfExists(imagePath);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores (puedes cambiarlo según tus necesidades)
            return false;
        }
    }
}
