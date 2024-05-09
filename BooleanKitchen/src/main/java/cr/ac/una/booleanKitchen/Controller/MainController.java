
package cr.ac.una.booleanKitchen.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.text.html.ImageView;

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

    @PostMapping("/uploadimage")
    public String uploadImage(@RequestParam("image") MultipartFile image) {
        // contexto
        // TODO recordar devuelve la ruta para guradarla en bd
        // Llamar a bd y traer el id de bd
        new ImagenService().saveImage(image, "01", "ORG", "BRAYAN");
        return "image/img";
    }

}
