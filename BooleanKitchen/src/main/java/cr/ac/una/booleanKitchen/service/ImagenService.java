/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author josue
 */
public class ImagenService {
    // aqui debemos decir la ruta donde las imagenes se van a guardar
    // (preferiblemente carpeta static.img)
    String uploadsDir = "src/main/resources/static";

    // metodo de ingreso de imagen a carpeta externa en proyecto
    public String insertImg(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Ruta donde se guardará la imagen en el directorio de recursos estáticos

                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadsDir + "/" + file.getOriginalFilename());
                Files.write(path, bytes);
                // retorna el nombre de la imagen
                return file.getOriginalFilename();
            } catch (IOException e) {
                // por si da error
                return "Error al subir la imagen.";
            }
        } else {
            // retorna vacio si la imagen esta vacia, para validad
            return " ";
        }
    }

}
