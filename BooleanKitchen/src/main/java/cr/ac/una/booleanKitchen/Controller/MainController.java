
package cr.ac.una.booleanKitchen.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cr.ac.una.booleanKitchen.service.ImagenService;

/**
 *
 * @author Daniel Briones
 */
@Controller
public class MainController {
    @GetMapping({ "/", "/home", "/index", "/login" })
    public String inicio() {
        System.out.println("cambio en rama de brayan");
        return "login";
    }

    @GetMapping("/upload")
    public String uploadImageForm() {
        return "image/img"; // Este es el nombre de la plantilla Thymeleaf
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile image) {

        if (!image.isEmpty()) {
            try {
                // Obtener los bytes de la imagen
                byte[] bytes = image.getBytes();

                // Ruta del directorio de destino
                String uploadDir = "Proyecto-de-Progra-IV/BooleanKitchen/src/main/resources/static/img/prueba/kendallfallas2018@gmail.com/";

                // Ruta del archivo de destino
                String filePath = uploadDir + image.getOriginalFilename();

                // Escribir los bytes en el archivo de destino
                Path path = Paths.get(filePath);
                Files.write(path, bytes);

                return "image/img";
            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
                return "error";
            }
        } else {
            // Manejar el caso de que el archivo esté vacío
            return "empty";
        }
        
    }

}
