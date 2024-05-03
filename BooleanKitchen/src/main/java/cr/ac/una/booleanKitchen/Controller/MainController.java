
package cr.ac.una.booleanKitchen.Controller;

import java.io.File;
import java.io.IOException;
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
        // Procesa la imagen aquí
        
        String url = "Proyecto-de-Progra-IV/BooleanKitchen/src/main/resources/static/img/prueba/kendallfallas2018@gmail.com/";
        
        try {
            File destino = new File(url + image.getOriginalFilename());
            destino.createNewFile();
            image.transferTo(destino);
            
        } catch (Exception e) {
           
        }


        return "image/img";
    }

}
