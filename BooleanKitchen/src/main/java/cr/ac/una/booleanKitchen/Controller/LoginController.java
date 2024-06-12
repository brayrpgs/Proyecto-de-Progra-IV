package cr.ac.una.booleanKitchen.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cr.ac.una.booleanKitchen.domain.User;
import cr.ac.una.booleanKitchen.service.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/controller_login")
public class LoginController {
    
    @Autowired   
    private UserRepository Urepo;

    @GetMapping("/access")
    public String guardar(@RequestParam("user") String usuario,
            @RequestParam("pass") String contrasena){
        
        if(!usuario.trim().isEmpty() || !contrasena.trim().isEmpty()){
            User u = Urepo.getUser(usuario);
            //System.out.println(u.toString());
            if(usuario.equals("admin") && contrasena.equals("123")){ //Aqui debe validar que 
                System.out.println("Acceso exitoso"); //coincida en BD
                return "redirect:/c_inicio/index"; // Cambiar** Aqui abre un menu principal
            }

            return "login"; // Debe mostrar un mensaje que no coinciden los datos
        }
        
        return "login"; //Los campos están vacios
    }
}
