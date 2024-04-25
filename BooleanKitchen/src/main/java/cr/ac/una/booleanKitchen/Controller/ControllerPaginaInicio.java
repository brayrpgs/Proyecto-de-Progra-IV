/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.booleanKitchen.Controller;

import cr.ac.una.booleanKitchen.Utilidades.Utilidades;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Daniel Briones
 */
@Controller
@RequestMapping("/c_inicio")
public class ControllerPaginaInicio {

    


    @GetMapping({"/index"})
    public String inicio(Model model){
        model.addAttribute("usuario", Utilidades.user);
        return "paginaInicio";
    }

    @GetMapping("/verRecetas")
    public String verRecetas(Model model){
        model.addAttribute("usuario", Utilidades.user);
        return "verRecetas";
    }
}
