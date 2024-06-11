package cr.ac.una.booleanKitchen.service;

import cr.ac.una.booleanKitchen.domain.Category;
import cr.ac.una.booleanKitchen.domain.Ingredient;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author kenda
 */


@Service
public class IngredientService {
    
    //Cambio a JPA

    @Autowired
    private final IngredientRepository ingredientRepository;

    
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public LinkedList<Category> getCategories(){
        return ingredientRepository.getCategories();
    }

    public Ingredient saveIngredient(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    public Optional<Ingredient> findByIdentificador(String IDENTIFICADOR){
        return ingredientRepository.findByCode(IDENTIFICADOR);
    }
    public Optional<Ingredient> findByNOMBRE(String NOMBRE){
        return ingredientRepository.findByName(NOMBRE);
    }

    @Transactional
    public void deleteIngredientByIDENTIFICADOR(String IDENTIFICADOR){
        ingredientRepository.deleteByCode(IDENTIFICADOR);
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