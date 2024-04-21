
package cr.ac.una.booleanKitchen.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
